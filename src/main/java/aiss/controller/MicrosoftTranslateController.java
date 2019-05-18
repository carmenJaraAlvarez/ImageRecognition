package aiss.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aiss.model.Member;

import aiss.model.repository.MemberRepository;
import aiss.model.resource.GoogleVisionResource;
import aiss.model.resource.MicrosoftTranslateResource;
import aiss.model.resource.UnplashResource;
import aiss.model.unplash.ImagesSearch;

public class MicrosoftTranslateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger log = Logger.getLogger(MicrosoftTranslateController.class.getName());
	
    public MicrosoftTranslateController() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accessToken = (String) req.getSession().getAttribute("Unplash-token");
		log.info("-------------------------------" + accessToken);
		String photoId = req.getParameter("id");
		log.info("-------------------------------"+photoId);
		String lang=req.getParameter("lang");
		if(lang==null || lang=="") {
			lang="en";
		}
		log.info("-------------------------------"+lang);
		String tagsString=req.getParameter("tags");	
		log.info("-------------------------------"+tagsString);
	    List<ImagesSearch> ims=new ArrayList<>();
	    if(photoId==null || photoId=="" || tagsString==null || tagsString=="") {
	    	log.info("No params");
	    	req.getRequestDispatcher("/unplashImagesList").forward(req, resp);
	    }
	    else {
	        //TODO change to ask for one will be more efficient
	        if (accessToken != null && !"".equals(accessToken)) {
	        	log.info("there is unsplash access token");
	            UnplashResource uResource = new UnplashResource(accessToken);	            
	            ims =uResource.getImages();    
	            log.info("---------------------------------Imgs size->"+ims.size());
	        } else {
	        	 log.info("there is NOT unsplash access token, redirec /authController/Unsplash");
	             req.getRequestDispatcher("/AuthController/Unplash").forward(req, resp);
	        }
	        ImagesSearch im=null;
	        for(ImagesSearch i: ims) {
	        	 
	        	 log.info(i.getId()+"******VS******"+photoId);
	        	if (i.getId().contentEquals(photoId)) {	        		 
	        		im=i;
	        		break;
	        	}
	        }
	        log.info(im.toString());
	        //log.info("uri image: "+im.getUrls().getSmall());
	        req.setAttribute("img", im);
	        req.setAttribute("id",photoId);
	        
		List<String> tags=new ArrayList<String>();		
		log.info("tagsString->"+tagsString);
		String tags2=tagsString.substring(1, tagsString.length()-1);
		tags = Arrays.asList(tags2.split("\\s*,\\s*"));				
		

		accessToken = (String) req.getSession().getAttribute("MicrosoftTranslate-token"); 
		MicrosoftTranslateResource mtResource = new MicrosoftTranslateResource(accessToken);
	            
	            List<String> translated = mtResource.getTranslation(tags,lang);

	            if (translated != null) {
	                req.setAttribute("translated", translated);
	                req.getRequestDispatcher("/MicrosoftTranslateListing.jsp").forward(req, resp);
	            } else {
	                log.info("The translated text is null... probably your bearer token has experied. Redirecting to Auth servlet.");
	                //req.getRequestDispatcher("/AuthControllerMicrosoftTranslate").forward(req, resp);
	            }
	    }
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
