package aiss.model.resource;




import java.io.IOException;
import org.apache.commons.*;

import org.apache.http.client.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import com.google.appengine.repackaged.com.google.gson.Gson;

import aiss.model.google.drive.FileItem;
import aiss.model.google.drive.Files;
import aiss.model.unplash.ImagesSearch;
import aiss.model.unplash.UnplashCollection;
import aiss.model.unplash.Urls;
import aiss.model.unplash.User;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;

public class UnplashResource {

    private static final Logger log = Logger.getLogger(UnplashResource.class.getName());

    private final String access_token;
    private String name;
    private final String uri = "https://api.unsplash.com/";


    public UnplashResource(String access_token) {
        this.access_token = access_token;
        this.name=this.getUsername();
    }


    /**
     *
     * @return Files
     */
    public List<ImagesSearch> getImages() {
    	 log.info(this.access_token);
    	 List<ImagesSearch> images=new ArrayList<>();
         ClientResource cr = null;

        try {
            cr = new ClientResource(uri+"users/"+name+"/photos/" + "?access_token=" + access_token);
            log.info(uri + "?access_token=" + access_token);
            ImagesSearch[] s = cr.get(ImagesSearch[].class);
            log.info(cr.getStatus()+"----"+ s.length);
           if(s.length>0) {
        	   images = Arrays.asList(s);
           }
  

        } catch (ResourceException re) {
            log.warning("Error when retrieving all images: " + cr.getResponse().getStatus());
        }

        return images;
    }
    
    public String getUsername() {
   	 log.info(this.access_token);
   	 String s="";
       ClientResource cr = null;

       try {
           cr = new ClientResource(uri+"me/?access_token=" + access_token);
           User user = cr.get(User.class);
           s=user.getUsername();
           log.info("--> result" +s);
          

       } catch (ResourceException re) {
           log.warning("Error when retrieving username: " + cr.getResponse().getStatus());
       }

       return s;
   }

    
    
    public boolean createCollection(String title, String description) throws IOException {

        ClientResource cr = null;
        log.info("in resource");
        try {
            cr = new ClientResource(uri+"collections" + "?access_token=" + access_token);

           UnplashCollection newc= new UnplashCollection();
           newc.setTitle("ulti");

           cr.post(newc);    

            log.info("Create Collection response->" + cr.getResponse().getStatus());
            return true;
        
        } catch (ResourceException re) {
            log.warning("Error when inserting file: " + cr.getResponse().getStatus());
            return false;
        }
      
    }
    //getCollection
    public void POSTRequest(String title) throws IOException {
        //final String POST_PARAMS =  "{\n" + "\"title2000\": 101,\"" + "\n}";
    	 String data = "title="+title+"&private=true";
//        System.out.println(data);
        System.out.println(access_token);
       // URL obj = new URL("https://api.unsplash.com/collections?access_token="+"ac7494081a0483fcbb270238435de2c60bb413fb34c496443c6e93f46eaeaa40");
        URL obj = new URL("https://api.unsplash.com/collections?access_token="+access_token);
        HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();

        postConnection.setRequestMethod("POST");

        postConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        
  //      postConnection.setRequestProperty("title", "adefgh");
 //       postConnection.setRequestProperty("User-agent", "adefgh");
        postConnection.setDoOutput(true);
        OutputStream os = postConnection.getOutputStream();
        OutputStreamWriter wr = new OutputStreamWriter(os, "UTF-8");
        wr.write(data);
        wr.flush();
        wr.close();
        int responseCode = postConnection.getResponseCode();
        System.out.println("POST Response Code :  " + responseCode);
        System.out.println("POST Response Message : " + postConnection.getResponseMessage());

        if (responseCode == HttpURLConnection.HTTP_CREATED) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                postConnection.getInputStream(),"UTF-8"));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in .readLine()) != null) {
                response.append(inputLine);
            } in .close();
            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("POST NOT WORKED");
        }
    }
	public List<UnplashCollection> getCollections() {
		List<UnplashCollection> res=new ArrayList<>();
		 ClientResource cr = null;
	        UnplashCollection lc= null;
	        try {
	        	String u=uri+"users/"+name+"/collections" + "?access_token=" + access_token;
	        	System.out.print(u);
	            cr = new ClientResource(u);
	            String result = cr.get(String.class);
	            log.warning("result: " + result);
	            UnplashCollection[] s = cr.get(UnplashCollection[].class);
	            log.info("--> result" +s[0].getId());
	            if(s.length>0) {
	            	res = Arrays.asList(s);
	            }
	            
	            //UnplashCollection has an atribute named "private" in json
	            //Json is a reserved word, so we are going to introduce the value in a new attribute "published"
	            //"published" will be in Map<String, Object> additionalProperties
	            checkPublished(res);
	            
	           return res;
	        } catch (ResourceException re) {
	            log.warning("Error when retrieving collections: " + cr.getResponse().getStatus());
	            return null;
	        }

	        
	}

		 private List<UnplashCollection> checkPublished(List<UnplashCollection> res) {
			 List<UnplashCollection> l=new ArrayList<UnplashCollection>();
			 for(UnplashCollection c: res){
				 if(c.getPrivate()) {
					 c.setAdditionalProperty("published", false);
				 }
				 else {
					 c.setAdditionalProperty("published", true);
				 }
			 }
		return l;
	}
		public void  addPhotoToCollection(String photoId, String collectionId) throws IOException {
		       
		    	 String data = "photo_id="+photoId;
		        System.out.println("data in addPhotoToCollection***"+data);
		        System.out.println(access_token);
		       // URL obj = new URL("https://api.unsplash.com/collections?access_token="+"ac7494081a0483fcbb270238435de2c60bb413fb34c496443c6e93f46eaeaa40");
		        URL obj = new URL("https://api.unsplash.com/collections/"+collectionId+"/add?access_token="+access_token);
		        HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();

		        postConnection.setRequestMethod("POST");

		        postConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		        
		  //      postConnection.setRequestProperty("title", "adefgh");
		 //       postConnection.setRequestProperty("User-agent", "adefgh");
		        postConnection.setDoOutput(true);
		        OutputStream os = postConnection.getOutputStream();
		        OutputStreamWriter wr = new OutputStreamWriter(os);
		        wr.write(data);
		        wr.flush();
		        wr.close();
		        int responseCode = postConnection.getResponseCode();
		        System.out.println("POST Response Code :  " + responseCode);
		        System.out.println("POST Response Message : " + postConnection.getResponseMessage());

		        if (responseCode == HttpURLConnection.HTTP_CREATED) { //success
		            BufferedReader in = new BufferedReader(new InputStreamReader(
		                postConnection.getInputStream()));
		            String inputLine;
		            StringBuffer response = new StringBuffer();
		            while ((inputLine = in .readLine()) != null) {
		                response.append(inputLine);
		            } in .close();
		            // print result
		            System.out.println(response.toString());
		        } else {
		            System.out.println("POST NOT WORKED");
		        }
		    }
		public void publish(String collectionId) throws IOException {
			// Put
			 String data = "private=false";
		        System.out.println("data in publishCollection***"+data);
		        System.out.println(access_token);
		        URL obj = new URL("https://api.unsplash.com/collections/"+collectionId+"?access_token="+access_token);
		        HttpURLConnection putConnection = (HttpURLConnection) obj.openConnection();

		        putConnection.setRequestMethod("PUT");

		        putConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	
		        putConnection.setDoOutput(true);
		        OutputStream os = putConnection.getOutputStream();
		        OutputStreamWriter wr = new OutputStreamWriter(os);
		        wr.write(data);
		        wr.flush();
		        wr.close();
		        int responseCode = putConnection.getResponseCode();
		        System.out.println("Response Code :  " + responseCode);
		        System.out.println("Response Message : " + putConnection.getResponseMessage());

		        if (responseCode == HttpURLConnection.HTTP_CREATED) { //success
		            BufferedReader in = new BufferedReader(new InputStreamReader(
		                putConnection.getInputStream()));
		            String inputLine;
		            StringBuffer response = new StringBuffer();
		            while ((inputLine = in .readLine()) != null) {
		                response.append(inputLine);
		            } in .close();
		            // print result
		            System.out.println(response.toString());
		        } else {
		            System.out.println("PUT NOT WORKED");
		        }
			
		}


		public void delete(String collectionId) {
			log.info(this.access_token);
		   	ClientResource cr = null;

		       try {
		           cr = new ClientResource(uri+"collections/"+collectionId+"/?access_token=" + access_token);
		           cr.delete();
		       } catch (ResourceException re) {
		           log.warning("Error when retrieving username: " + cr.getResponse().getStatus());
		       }

		}
    }




