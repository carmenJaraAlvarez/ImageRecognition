package aiss.controller;




import aiss.model.google.drive.FileItem;
import aiss.model.resource.GoogleDriveResource;
import aiss.model.resource.UnplashResource;
import aiss.model.unplash.ImagesSearch;
import aiss.model.unplash.UnplashCollection;
import aiss.model.unplash.Urls;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnplashCreateCollectionController extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(UnplashCreateCollectionController.class.getName());

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

    	 String accessToken = (String) req.getSession().getAttribute("Unplash-token");
    	 
         //TODO
    	 String title="New collection";

    	 
    	 
         if (accessToken != null && !"".equals(accessToken)) {
             if (title != null && !"".equals(title)) {
            	 UnplashResource uResource = new UnplashResource(accessToken);
            	 log.info("in create controller");
                 uResource.POSTRequest(title);
                 
                 req.setAttribute("message", "created");
                 req.getRequestDispatcher("/unplashCollectionsList").forward(req, resp);
             } else {
                 req.setAttribute("message", "You must provide a valid title");

                 req.getRequestDispatcher("unplashCreateCollection.jsp").forward(req, resp);
             }
         } else {
             log.info("Trying to access Unplash without an access token, redirecting to OAuth servlet");
             req.getRequestDispatcher("/AuthController/Unplash").forward(req, resp);
         }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doGet(req, resp);
    }
}
