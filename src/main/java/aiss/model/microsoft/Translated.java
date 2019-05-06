
package aiss.model.microsoft;

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
    "detectedLanguage",
    "translations"
})
public class Translated {

    @JsonProperty("detectedLanguage")
    private DetectedLanguage detectedLanguage;
    @JsonProperty("translations")
    private List<Translation> translations = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("detectedLanguage")
    public DetectedLanguage getDetectedLanguage() {
        return detectedLanguage;
    }

    @JsonProperty("detectedLanguage")
    public void setDetectedLanguage(DetectedLanguage detectedLanguage) {
        this.detectedLanguage = detectedLanguage;
    }

    @JsonProperty("translations")
    public List<Translation> getTranslations() {
        return translations;
    }

    @JsonProperty("translations")
    public void setTranslations(List<Translation> translations) {
        this.translations = translations;
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
