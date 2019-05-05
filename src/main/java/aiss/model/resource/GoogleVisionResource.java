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
     * @return tags
     * @throws IOException 
     */
    public List<String> getTags() throws IOException {
    	
        ClientResource cr = null;
        List<String> tags = new ArrayList<String>();
        GoogleVisionSearch gvs=null;
        try {
            cr = new ClientResource(uri + "?access_token=" + access_token);
          
            
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
            String imageUri="https://images.unsplash.com/photo-1554559806-de518318809d?ixlib=rb-1.2.1&q=85&fm=jpg&crop=entropy&cs=srgb&ixid=eyJhcHBfaWQiOjY4ODE4fQ?access_token=31bed6f3bf59f0602c0802ede58fc35a58801898dd51c576b113b33e2b59aaff";
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
           
             gvs=cr.post(gvr, GoogleVisionSearch.class);
             Response r=gvs.getResponses().get(0);
//            org.restlet.Response resp=cr.getResponse();  
//            result=result + resp.getStatus().getDescription();
//            GoogleVisionSearch gvs=cr.post();
//            List<LabelAnnotation> annotations =gvs.getResponses().get(0).getLabelAnnotations();
//            for(LabelAnnotation la: annotations) {
//            	tags.add(la.getDescription());
//            }
             for(LabelAnnotation l: r.getLabelAnnotations()) {
            	 tags.add(l.getDescription());
             }
        } catch (ResourceException re) {
            log.warning("Error when retrieving tags: " + cr.getResponse().getStatus());
        }

       
		return tags;
    }


  

}
