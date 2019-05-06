package aiss.model.resource;



import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.MediaType;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

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
        List<String> translated = null;
        try {
            cr = new ClientResource(uri  + lang);
            //String result = cr.get(String.class);
            //translated = cr.get(Files.class);
            
        } catch (ResourceException re) {
            log.warning("Error translating " + cr.getResponse().getStatus());
        }

        return translated;

    }




}
