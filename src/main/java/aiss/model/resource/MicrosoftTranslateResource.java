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
   

    public MicrosoftTranslateResource(String access_token) {
        this.access_token = access_token;
    }

    /**
     *
     *
     */
    public List<String> getTranslation(List<String> ls, String lang) {
        ClientResource cr = null;
        List<String> translated = new ArrayList<>();
        try {
            cr = new ClientResource(uri  + lang);
            log.info("uri-> " + uri  + lang);
            Series<Header> headers = (Series<Header>) cr.getRequestAttributes().get("org.restlet.http.headers"); 
            if (headers == null) { 
            	 log.info("headers is null " );
            	headers = new Series<Header>(Header.class); 
            	} 
            log.info("Bearer " +access_token);
            headers.add("Authoritation", "Bearer " +access_token );
            
            //String result = cr.get(String.class);
            TranslateRequest[] mtr=new TranslateRequest[ls.size()];
            for(int i=0; i<1; i++) {
            	//mtr[i].setText(ls.get(i));
            }
            
            Translated[] translations=cr.post(mtr, Translated[].class);
            log.info("translation response " + cr.getResponse().getStatus());
        } catch (ResourceException re) {
            log.warning("Error translating " + cr.getResponse().getStatus());
        }

        return translated;//TODO

    }




}
