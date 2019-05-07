package aiss.model.resource;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.Header;
import org.restlet.data.MediaType;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;
import org.restlet.util.Series;


import aiss.model.microsoft.TranslateRequest;
import aiss.model.microsoft.Translated;

public class MicrosoftTranslateResource {

    private static final Logger log = Logger.getLogger(MicrosoftTranslateResource.class.getName());

    private final String access_token;
    private final String uri = "https://api.cognitive.microsofttranslator.com/translate?api-version=3.0&to=";
	private final String uri0="https://api.cognitive.microsoft.com/sts/v1.0/issuetoken?Subscription-Key=";
	private final String secret="4ab65da9248a4429af8ec5eb91d16444";

    public MicrosoftTranslateResource(String access_token) {
        this.access_token = access_token;
    }

    /**
     *
     *
     */
    public List<String> getTranslation(List<String> tags, String lang) {

		String access_token="";		
		ClientResource cr0 = null;
		cr0 = new ClientResource(uri0  + secret);
		log.info(uri0  + secret);
		cr0.setRequestEntityBuffering(true);
		access_token = cr0.post(" ",String.class);
		  log.info("token__________________ "+ access_token);
		 if (access_token != null && !"".equals(access_token)) {
			   ClientResource cr = null;
		        List<String> translated = new ArrayList<>();
		        try {
		            cr = new ClientResource(uri  + lang);
		            log.info("uri-> " + uri  + lang);

		            ChallengeResponse chr = new ChallengeResponse(
		                    ChallengeScheme.HTTP_OAUTH_BEARER);
		       chr.setRawValue(access_token);
		       cr.setChallengeResponse(chr);
		      
		            //String result = cr.get(String.class);
		            
		  
		            TranslateRequest[] mtr=new TranslateRequest[tags.size()];
		            
		            for(int i=0; i<tags.size();i++) {
		                mtr[i]=new TranslateRequest();
		                mtr[i].setText(tags.get(i));
		            }

		            Translated[] translations=cr.post(mtr, Translated[].class);
		            log.info("translation response " + cr.getResponse().getStatus());
		            for(int i=0; i<tags.size();i++) {
		            	translated.add(translations[i].getTranslations().get(0).getText());
		            	log.info(">>>>>>>>>>>>>>" + translated.get(i));
		            }
		          
		        } catch (ResourceException re) {
		            log.warning("Error translating " + cr.getResponse().getStatus());
		        }

		        return translated;
		 }
		 else {
			 log.info("error trying to access Microsoft Translate Token");
			 return null;
		 }
     

    }




}
