
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
    "url",
    "pageTitle",
    "partialMatchingImages",
    "fullMatchingImages"
})
public class PagesWithMatchingImage {

    @JsonProperty("url")
    private String url;
    @JsonProperty("pageTitle")
    private String pageTitle;
    @JsonProperty("partialMatchingImages")
    private List<PartialMatchingImage_> partialMatchingImages = null;
    @JsonProperty("fullMatchingImages")
    private List<FullMatchingImage_> fullMatchingImages = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("pageTitle")
    public String getPageTitle() {
        return pageTitle;
    }

    @JsonProperty("pageTitle")
    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    @JsonProperty("partialMatchingImages")
    public List<PartialMatchingImage_> getPartialMatchingImages() {
        return partialMatchingImages;
    }

    @JsonProperty("partialMatchingImages")
    public void setPartialMatchingImages(List<PartialMatchingImage_> partialMatchingImages) {
        this.partialMatchingImages = partialMatchingImages;
    }

    @JsonProperty("fullMatchingImages")
    public List<FullMatchingImage_> getFullMatchingImages() {
        return fullMatchingImages;
    }

    @JsonProperty("fullMatchingImages")
    public void setFullMatchingImages(List<FullMatchingImage_> fullMatchingImages) {
        this.fullMatchingImages = fullMatchingImages;
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
