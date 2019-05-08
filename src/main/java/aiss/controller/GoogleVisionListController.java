package aiss.controller;

import aiss.model.google.drive.Files;
import aiss.model.resource.GoogleDriveResource;
import aiss.model.resource.GoogleVisionResource;
import aiss.model.resource.UnplashResource;
import aiss.model.unplash.ImagesSearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoogleVisionListController extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(GoogleVisionListController.class.getName());

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    	String accessToken = (String) req.getSession().getAttribute("Unplash-token");
        String photoId = req.getParameter("id");
        List<ImagesSearch> ims=new ArrayList<>();
        String accessTokenG = (String) req.getSession().getAttribute("GoogleVision-token");
        //TODO change to ask for one
        if (accessToken != null && !"".equals(accessToken)) {
        	
            UnplashResource uResource = new UnplashResource(accessToken);
            log.info("there is unsplash access token");
            ims =uResource.getImages();            
        } else {
            log.info("Trying to access  unsplash without an access token, redirecting to OAuth servlet");
            req.getRequestDispatcher("/AuthController/Unplash").forward(req, resp);
        }
        ImagesSearch im=null;
        for(ImagesSearch i: ims) {
        	if (i.getId().equals(photoId)) {
        		im=i;
        		break;
        	}
        }
        log.info("uri image: "+im.getUrls().getSmall());
        req.setAttribute("img", im);
        

        if (accessTokenG != null && !"".equals(accessTokenG)) {

            GoogleVisionResource gdResource = new GoogleVisionResource(accessTokenG);
            List<String> tags = gdResource.getTags(im.getUrls().getFull(), accessToken, accessTokenG);

            if (tags != null) {
                req.setAttribute("tags", tags);
                req.getRequestDispatcher("/googleVisionListing.jsp").forward(req, resp);
            } else {
                log.info("The tags returned are null... probably your token has experied. Redirecting to OAuth servlet.");
                req.getRequestDispatcher("/AuthController/GoogleVision").forward(req, resp);
            }
        } else {
            log.info("Trying to access Google Vision without an access token, redirecting to OAuth servlet");
            req.getRequestDispatcher("/AuthController/GoogleVision").forward(req, resp);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doGet(req, resp);
    }
}
