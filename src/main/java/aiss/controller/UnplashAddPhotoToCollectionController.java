package aiss.controller;




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

import com.google.api.client.repackaged.com.google.common.base.Strings;

public class UnplashAddPhotoToCollectionController extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(UnplashAddPhotoToCollectionController.class.getName());

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    	String collectionId=null;
    	String collection=null;
    	String photoId=null;
        photoId = req.getParameter("id");
        collection=req.getParameter("collection");
        if((collection==null) || collection=="" || photoId==null || photoId=="") {
        	log.info("Not params");
       	 	req.getRequestDispatcher("/unplashCollectionsList").forward(req, resp);
        }else {
        String accessToken = (String) req.getSession().getAttribute("Unplash-token");
        log.info("++"+collection + (collection==null));
        //if collection contains *new* create collection
        if(collection.contains("*(new)*")) {
        	String title= collection.substring(0, collection.length()-8);
        	log.info("NEW----------------"+ title);
        	 
             if (accessToken != null && !"".equals(accessToken)) {
                 if (title != null && !"".equals(title)) {
                	 UnplashResource uResource = new UnplashResource(accessToken);
                	 log.info("creating");
                     uResource.POSTRequest(title);
                     List<UnplashCollection> l=uResource.getCollections();
                     for (UnplashCollection c: l) {
                    	 String t=c.getTitle();
                    	 log.info("]]"+t+"**"+title); //                   	
                    	 if(t.contentEquals(title)) {
                    		 collectionId=c.getId().toString();
                    		 log.info(collectionId);
                    		 break;
                    	 }
                    	 
                     }
                     req.getRequestDispatcher("/unplashCollectionsList").forward(req, resp);
                 } else {
                     req.setAttribute("message", "It is not a valid title to collection");

                     req.getRequestDispatcher("unplashClasificator.jsp").forward(req, resp);
                 }
             } else {
                 log.info("Trying to access Unplash without an access token, redirecting to OAuth servlet");
                 req.getRequestDispatcher("/AuthController/Unplash").forward(req, resp);
             }
        	
        }
        else {
        	 //get collections
        	 if (accessToken != null && !"".equals(accessToken)) {
                 if (collection != null && !"".equals(collection)) {
                	 UnplashResource uResource = new UnplashResource(accessToken);
                	 log.info("getting collections");
                	 List<UnplashCollection> l=uResource.getCollections();
                     // for collections if title == >>  collectionId
                     for (UnplashCollection c: l) {
                    	 log.info("]]]]]]]]]]]]]]]]]]]]]]"+(String) c.getTitle()+"**"+collection);
                    	 if(c.getTitle().contentEquals(collection)) {
                    		 collectionId=c.getId().toString();
                    	 }
                     }
                 }
                 else {
                	 log.info("No title");
                	 req.setAttribute("message", "It is not a valid title to collection");
                	 req.getRequestDispatcher("unplashClasificator.jsp").forward(req, resp);
                 }
        	 }
        	 else {
                 log.info("Trying to access Unplash without an access token, redirecting to OAuth servlet");
                 req.getRequestDispatcher("/AuthController/Unplash").forward(req, resp);
             }
        }
       
        //adding
       
        if (accessToken != null && !"".equals(accessToken)) {
        	
            UnplashResource uResource = new UnplashResource(accessToken);
            log.info("there is access token in ADD***");
            uResource.addPhotoToCollection(photoId,collectionId);
                req.getRequestDispatcher("/unplashImagesList").forward(req, resp);
            
        } else {
            log.info("Trying to access without an access token, redirecting to OAuth servlet");
            req.getRequestDispatcher("/AuthController/Unplash").forward(req, resp);
        }
    }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doGet(req, resp);
    }
}
