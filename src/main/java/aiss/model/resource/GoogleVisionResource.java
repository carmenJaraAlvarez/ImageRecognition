package aiss.model.resource;

import aiss.model.google.drive.FileItem;
import aiss.model.google.drive.Files;
import aiss.model.google.vision.Feature;
import aiss.model.google.vision.GoogleVisionRequest;
import aiss.model.google.vision.GoogleVisionSearch;
import aiss.model.google.vision.Image;
import aiss.model.google.vision.LabelAnnotation;
import aiss.model.google.vision.Request;
import aiss.model.google.vision.Response;
import aiss.model.google.vision.Source;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.MediaType;
import org.restlet.data.Method;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class GoogleVisionResource {

    private static final Logger log = Logger.getLogger(GoogleVisionResource.class.getName());

    private final String access_token;
    private final String uri = "https://vision.googleapis.com/v1/images:annotate";


    public GoogleVisionResource(String access_token) {
        this.access_token = access_token;
    }

    /**
     *
     * @param accessTokenG 
     * @param accessToken 
     * @return tags
     * @throws IOException 
     */
    public List<String> getTags(String unsplashUri, String unsplashToken, String accessTokenG) throws IOException {
    	
        ClientResource cr = null;
        List<String> tags = new ArrayList<String>();
        GoogleVisionSearch gvs=null;
        try {
        	 log.info("Init try---------------------------");
            cr = new ClientResource(uri);
            log.info("cr---------------------------");
            ChallengeResponse chr = new ChallengeResponse(
                    ChallengeScheme.HTTP_OAUTH_BEARER);
            chr.setRawValue(access_token);
            log.info("chr---------------------------");
            cr.setChallengeResponse(chr);
            log.info("cr.chr---------------------------");
            String text = "{\r\n" + 
            		"  \"requests\": [\r\n" + 
            		"    {\r\n" + 
            		"      \"image\": {\r\n" + 
            		"        \"source\": {\r\n" + 
            		"          \"imageUri\": \"https://raw.githubusercontent.com/karolmajek/GCP-Vision-API/master/images/kinship.jpg\"\r\n" + 
            		"        }\r\n" + 
            		"      },\r\n" + 
            		"      \"features\": [\r\n" + 
            		"        {\r\n" + 
            		"          \"type\": \"LABEL_DETECTION\",\r\n" + 
            		"          \"maxResults\": 5\r\n" + 
            		"        }\r\n" + 
            		"      ]\r\n" + 
            		"    }\r\n" + 
            		"  ]\r\n" + 
            		"}";
           
            GoogleVisionRequest gvr= new GoogleVisionRequest();

            
            //String imageUri= "https://raw.githubusercontent.com/karolmajek/GCP-Vision-API/master/images/kinship.jpg";
            String imageUri=unsplashUri + "?access_token=" + unsplashToken;
            log.info("Image uri in Unsplash Resource->"+imageUri);
            String type= "LABEL_DETECTION";
            
            Source source=new Source();
            source.setImageUri(imageUri);
            
            Image image=new Image();
            image.setSource(source);
            
            Feature feature=new Feature();
            feature.setType(type);
            feature.setMaxResults(5);
            
            List<Feature> fs= new ArrayList<>();
            fs.add(feature);
            
            Request rq=new Request();
            rq.setImage(image);
            rq.setFeatures(fs);
            
            List<Request> rqs=new ArrayList<>();
            rqs.add(rq);
            
            gvr.setRequests(rqs);
            log.info("cr.chr---------------------------");
             gvs=cr.post(gvr, GoogleVisionSearch.class);
            
             log.info("post status->"+cr.getStatus()+". Estate 401->"+(cr.getStatus().getCode()==401));
             if(cr.getStatus().getCode()==401){
            	 tags=null;
             }
             else {
	             Response r=gvs.getResponses().get(0);
	
	             for(LabelAnnotation l: r.getLabelAnnotations()) {
	            	 tags.add(l.getDescription());
	             }
             }
        } catch (ResourceException re) {
            log.warning("Error when retrieving tags: " + cr.getResponse().getStatus());
        }

       
		return tags;
    }


  

}
