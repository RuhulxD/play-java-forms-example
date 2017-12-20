package controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.omertron.omdbapi.model.OmdbVideoFull;
import play.data.validation.Constraints;

public class VideoData extends OmdbVideoFull {

    @Constraints.Required
    @JsonProperty("tags")
    private String tags;

    @Constraints.Required
    @JsonProperty("youtubeURL")
    private String youtubeUrl;

    @Constraints.Required
    @JsonProperty("Trailer")
    private String trailer;

    public VideoData(@Constraints.Required String title, @Constraints.Required String year, @Constraints.Required String type, String poster, @Constraints.Required String genre, String actors, @Constraints.Required String tags, @Constraints.Required String youtubeUrl, @Constraints.Required String trailer) {
        super(title, year, type, poster, genre, actors);
        this.tags = tags;
        this.youtubeUrl = youtubeUrl;
        this.trailer = trailer;
    }

    public VideoData() {
        super("title", "year", "type", "poster", "genre", "actors");
    }

    //<editor-fold defaultstate="collapsed" desc="Getter Methods">
    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Setter Methods">
    public void setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }
    //</editor-fold>
}
