
package aiss.model.unplash;

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
    "id",
    "title",
    "description",
    "published_at",
    "updated_at",
    "curated",
    "featured",
    "total_photos",
    "private",
    "share_key",
    "tags",
    "cover_photo",
    "preview_photos",
    "user",
    "links"
})
public class UnplashCollection {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private Object description;
    @JsonProperty("published_at")
    private String publishedAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("curated")
    private Boolean curated;
    @JsonProperty("featured")
    private Boolean featured;
    @JsonProperty("total_photos")
    private Integer totalPhotos;
    @JsonProperty("private")
    private Boolean _private;
    @JsonProperty("share_key")
    private String shareKey;
    @JsonProperty("tags")
    private List<Object> tags = null;
    @JsonProperty("cover_photo")
    private Object coverPhoto;
    @JsonProperty("preview_photos")
    private Object previewPhotos;
    @JsonProperty("user")
    private User user;
    @JsonProperty("links")
    private Links_ links;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("description")
    public Object getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(Object description) {
        this.description = description;
    }

    @JsonProperty("published_at")
    public String getPublishedAt() {
        return publishedAt;
    }

    @JsonProperty("published_at")
    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    @JsonProperty("updated_at")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("curated")
    public Boolean getCurated() {
        return curated;
    }

    @JsonProperty("curated")
    public void setCurated(Boolean curated) {
        this.curated = curated;
    }

    @JsonProperty("featured")
    public Boolean getFeatured() {
        return featured;
    }

    @JsonProperty("featured")
    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    @JsonProperty("total_photos")
    public Integer getTotalPhotos() {
        return totalPhotos;
    }

    @JsonProperty("total_photos")
    public void setTotalPhotos(Integer totalPhotos) {
        this.totalPhotos = totalPhotos;
    }

    @JsonProperty("private")
    public Boolean getPrivate() {
        return _private;
    }

    @JsonProperty("private")
    public void setPrivate(Boolean _private) {
        this._private = _private;
    }

    @JsonProperty("share_key")
    public String getShareKey() {
        return shareKey;
    }

    @JsonProperty("share_key")
    public void setShareKey(String shareKey) {
        this.shareKey = shareKey;
    }

    @JsonProperty("tags")
    public List<Object> getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(List<Object> tags) {
        this.tags = tags;
    }

    @JsonProperty("cover_photo")
    public Object getCoverPhoto() {
        return coverPhoto;
    }

    @JsonProperty("cover_photo")
    public void setCoverPhoto(Object coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    @JsonProperty("preview_photos")
    public Object getPreviewPhotos() {
        return previewPhotos;
    }

    @JsonProperty("preview_photos")
    public void setPreviewPhotos(Object previewPhotos) {
        this.previewPhotos = previewPhotos;
    }

    @JsonProperty("user")
    public User getUser() {
        return user;
    }

    @JsonProperty("user")
    public void setUser(User user) {
        this.user = user;
    }

    @JsonProperty("links")
    public Links_ getLinks() {
        return links;
    }

    @JsonProperty("links")
    public void setLinks(Links_ links) {
        this.links = links;
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
