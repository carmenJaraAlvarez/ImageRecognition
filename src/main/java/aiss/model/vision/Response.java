
package aiss.model.vision;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "landmarkAnnotations",
    "webDetection"
})
public class Response {

    @JsonProperty("landmarkAnnotations")
    private List<LandmarkAnnotation> landmarkAnnotations = null;
    @JsonProperty("webDetection")
    private WebDetection webDetection;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("landmarkAnnotations")
    public List<LandmarkAnnotation> getLandmarkAnnotations() {
        return landmarkAnnotations;
    }

    @JsonProperty("landmarkAnnotations")
    public void setLandmarkAnnotations(List<LandmarkAnnotation> landmarkAnnotations) {
        this.landmarkAnnotations = landmarkAnnotations;
    }

    @JsonProperty("webDetection")
    public WebDetection getWebDetection() {
        return webDetection;
    }

    @JsonProperty("webDetection")
    public void setWebDetection(WebDetection webDetection) {
        this.webDetection = webDetection;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
