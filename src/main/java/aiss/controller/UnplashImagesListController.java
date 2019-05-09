package aiss.controller;




import aiss.model.resource.UnplashResource;
import aiss.model.unplash.ImagesSearch;
import aiss.model.unplash.Urls;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnplashImagesListController extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(UnplashImagesListController.class.getName());

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String accessToken = (String) req.getSession().getAttribute("Unplash-token");
        String name = req.getParameter("name");
        
        if (accessToken != null && !"".equals(accessToken)) {
        	
            UnplashResource uResource = new UnplashResource(accessToken);
            log.info("there is access token");
//            Files files = gdResource.getFiles();
            List<ImagesSearch> im =uResource.getImages();
            if (im != null) {
            	log.info("--> not null__"+im.size());
            
            	req.setAttribute("images", im);
                req.getRequestDispatcher("/unplashImagesListing.jsp").forward(req, resp);
            } else {
               log.info("The images returned are null... probably your token has experied. Redirecting to OAuth servlet.");
               req.getRequestDispatcher("/AuthController/Unplash").forward(req, resp);
               }
        } else {
            log.info("Trying to access Imgurwithout an access token, redirecting to OAuth servlet");
            req.getRequestDispatcher("/AuthController/Unplash").forward(req, resp);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doGet(req, resp);
    }
}
