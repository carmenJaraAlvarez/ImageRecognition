package aiss.controller;

import aiss.model.Record;
import aiss.model.google.drive.Files;
import aiss.model.repository.AuxRepository;
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
    	String accessTokenUnsplash = (String) req.getSession().getAttribute("Unplash-token");
    	String accessToken = (String) req.getSession().getAttribute("GoogleVision-token");   
    	String unsplashId=null;
        if (accessTokenUnsplash != null && !"".equals(accessTokenUnsplash)) {
            UnplashResource uResource = new UnplashResource(accessTokenUnsplash);
            unsplashId=uResource.getUsername();

        }
        else {
        
            log.info("Trying to access  unsplash without an access token, redirecting to OAuth servlet");
            req.getRequestDispatcher("/unplashImagesList").forward(req, resp);
        }
        String photoId = req.getParameter("id");
        AuxRepository repo=AuxRepository.getInstance();
        log.info("idphoto: "+photoId);
        //id yes, token no
        if((photoId!=null && photoId!="") && (accessToken==null || accessToken=="")) {
        	//save id in repo, go to auth        	        	
        	repo.addRecord(unsplashId, null, null, photoId);
            log.info("Trying to access  unsplash without an access token, redirecting to OAuth servlet");
            req.getRequestDispatcher("/AuthController/GoogleVision").forward(req, resp);
        }
        //id no, token yes
        if((photoId==null || photoId=="") && (accessToken!=null && accessToken!="")) {
        	 log.info("id null y accesstoken");
        	//take id from repo, go on
        	try {
        	Record r=repo.findByUnsplashId(unsplashId);
        	photoId=r.getPhotoId();
        	//if no id >> go to image list
        	}
        	catch(Exception e) {
        		log.info(e.getMessage());
        		req.getRequestDispatcher("/unplashImagesList").forward(req, resp);
        	}
        	if(photoId==null) {
        		 log.info("No photo id");
                 req.getRequestDispatcher("/unplashImagesList").forward(req, resp);
        	}
        }
               //id no, token no
        if((photoId==null || photoId=="") && (accessToken==null || accessToken=="")) {
        	//go to auth
            log.info("Trying to access  unsplash without an access token, redirecting to OAuth servlet");
            req.getRequestDispatcher("/AuthController/GoogleVision").forward(req, resp);
        }
        //id yes, token yes
        if((photoId!=null && photoId!="") && (accessToken!=null && accessToken!="")) {
        	log.info("photo and token");
	        	//go on	       
	        List<ImagesSearch> ims=new ArrayList<>();
	        String accessTokenG = (String) req.getSession().getAttribute("GoogleVision-token");
	        //TODO change to ask for one
	        log.info("token g: "+ accessTokenG);
	        if (accessTokenUnsplash != null && !"".equals(accessTokenUnsplash)) {
	        	
	            UnplashResource uResource = new UnplashResource(accessTokenUnsplash);
	            log.info("** there is  access token");
	            ims =uResource.getImages();
	            log.info("--------------------------------");
	        } else {
	            log.info("Trying to access  unsplash without an access token, redirecting to OAuth servlet");
	            req.getRequestDispatcher("/AuthController/Unplash").forward(req, resp);
	        }
	        ImagesSearch im=null;
	        log.info("--------------------------------");
	        for(ImagesSearch i: ims) {
	        	if (i.getId().equals(photoId)) {
	        		im=i;
	        		 log.info("find photo");
	        		break;
	        	}
	        }
	        log.info("uri image: "+im.getUrls().getSmall());
	        req.setAttribute("img", im);
	        
	        log.info("--------------------------------");
	        if (accessTokenG != null && !"".equals(accessTokenG)) {
	
	            GoogleVisionResource gdResource = new GoogleVisionResource(accessTokenG);
	            List<String> tags = gdResource.getTags(im.getUrls().getFull(), accessToken, accessTokenG);
	            //if response==401 to req.getRequestDispatcher("/AuthController/GoogleVision").forward(req, resp);
	            if (tags != null && !tags.isEmpty()) {
	            	log.info("tags ok");
	                req.setAttribute("tags", tags);
	                log.info("tag 1"+tags.get(0));
	                req.getRequestDispatcher("/GoogleVisionListing.jsp").forward(req, resp);
	            } else {
	                log.info("The tags returned are null... probably your token has experied. Redirecting to OAuth servlet.");
	                req.getRequestDispatcher("/AuthController/GoogleVision").forward(req, resp);
	            }
	        } else {
	            log.info("Trying to access Google Vision without an access token, redirecting to OAuth servlet");
	            req.getRequestDispatcher("/AuthController/GoogleVision").forward(req, resp);
	        }
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doGet(req, resp);
    }
}
