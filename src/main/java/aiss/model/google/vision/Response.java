
package aiss.model.google.vision;

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
    "labelAnnotations"
})
public class Response {

    @JsonProperty("labelAnnotations")
    private List<LabelAnnotation> labelAnnotations = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("labelAnnotations")
    public List<LabelAnnotation> getLabelAnnotations() {
        return labelAnnotations;
    }

    @JsonProperty("labelAnnotations")
    public void setLabelAnnotations(List<LabelAnnotation> labelAnnotations) {
        this.labelAnnotations = labelAnnotations;
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
