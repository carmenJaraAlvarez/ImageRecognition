
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
    "webEntities",
    "fullMatchingImages",
    "partialMatchingImages",
    "pagesWithMatchingImages",
    "visuallySimilarImages",
    "bestGuessLabels"
})
public class WebDetection {

    @JsonProperty("webEntities")
    private List<WebEntity> webEntities = null;
    @JsonProperty("fullMatchingImages")
    private List<FullMatchingImage> fullMatchingImages = null;
    @JsonProperty("partialMatchingImages")
    private List<PartialMatchingImage> partialMatchingImages = null;
    @JsonProperty("pagesWithMatchingImages")
    private List<PagesWithMatchingImage> pagesWithMatchingImages = null;
    @JsonProperty("visuallySimilarImages")
    private List<VisuallySimilarImage> visuallySimilarImages = null;
    @JsonProperty("bestGuessLabels")
    private List<BestGuessLabel> bestGuessLabels = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("webEntities")
    public List<WebEntity> getWebEntities() {
        return webEntities;
    }

    @JsonProperty("webEntities")
    public void setWebEntities(List<WebEntity> webEntities) {
        this.webEntities = webEntities;
    }

    @JsonProperty("fullMatchingImages")
    public List<FullMatchingImage> getFullMatchingImages() {
        return fullMatchingImages;
    }

    @JsonProperty("fullMatchingImages")
    public void setFullMatchingImages(List<FullMatchingImage> fullMatchingImages) {
        this.fullMatchingImages = fullMatchingImages;
    }

    @JsonProperty("partialMatchingImages")
    public List<PartialMatchingImage> getPartialMatchingImages() {
        return partialMatchingImages;
    }

    @JsonProperty("partialMatchingImages")
    public void setPartialMatchingImages(List<PartialMatchingImage> partialMatchingImages) {
        this.partialMatchingImages = partialMatchingImages;
    }

    @JsonProperty("pagesWithMatchingImages")
    public List<PagesWithMatchingImage> getPagesWithMatchingImages() {
        return pagesWithMatchingImages;
    }

    @JsonProperty("pagesWithMatchingImages")
    public void setPagesWithMatchingImages(List<PagesWithMatchingImage> pagesWithMatchingImages) {
        this.pagesWithMatchingImages = pagesWithMatchingImages;
    }

    @JsonProperty("visuallySimilarImages")
    public List<VisuallySimilarImage> getVisuallySimilarImages() {
        return visuallySimilarImages;
    }

    @JsonProperty("visuallySimilarImages")
    public void setVisuallySimilarImages(List<VisuallySimilarImage> visuallySimilarImages) {
        this.visuallySimilarImages = visuallySimilarImages;
    }

    @JsonProperty("bestGuessLabels")
    public List<BestGuessLabel> getBestGuessLabels() {
        return bestGuessLabels;
    }

    @JsonProperty("bestGuessLabels")
    public void setBestGuessLabels(List<BestGuessLabel> bestGuessLabels) {
        this.bestGuessLabels = bestGuessLabels;
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
