package aiss.controller;




import aiss.model.resource.UnplashResource;
import aiss.model.unplash.ImagesSearch;
import aiss.model.unplash.UnplashCollection;
import aiss.model.unplash.Urls;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnplashClasificatorController extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(UnplashClasificatorController.class.getName());

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String accessToken = (String) req.getSession().getAttribute("Unplash-token");
        String id=req.getParameter("id");
        String translated0=req.getParameter("translated");
        
        List<String> collectionsForSelect=new ArrayList<String>();
        
        if (accessToken != null && !"".equals(accessToken)) {
        	List<String> translated=new ArrayList<String>();		
    		String translated1=translated0.substring(1, translated0.length()-1);
    		translated = Arrays.asList(translated1.split("\\s*,\\s*"));		
    		
        	req.setAttribute("id", id);
        	req.setAttribute("translated", translated);

        	//getcollections
        	 UnplashResource uResource = new UnplashResource(accessToken);
             List<UnplashCollection> l=uResource.getCollections();
        	//each collection name in collectionsForSelect
             //if translated== any collection name >> remove
             for(UnplashCollection u: l) {
            	 
            	 String title=u.getTitle();
            	 log.info(title);
            	 collectionsForSelect.add(title);
            	 for(String t: translated) {
            		 if(t==title) {
            			 translated.remove(t);
            		 }
            	 }
             }
             log.info("#################"+ collectionsForSelect.size());
        	//each translated in collectionsForSelect with *(new)*
             for(String t:translated) {
            	 collectionsForSelect.add(t+ " *(new)*");
             }
        	req.setAttribute("collectionsForSelect", collectionsForSelect);
        	req.getRequestDispatcher("/unplashClasificator.jsp").forward(req, resp);//TODO
        } else {
            log.info("Trying to access without an access token, redirecting to OAuth servlet");
            req.getRequestDispatcher("/AuthController/Unplash").forward(req, resp);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doGet(req, resp);
    }
}
