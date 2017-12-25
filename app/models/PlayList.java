package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Set;

@Entity
public class PlayList {

    @Id
    @Constraints.Required
    @JsonProperty("Name")
    String id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonProperty("Videos")
    Set<VideoBasic> videos;

    public PlayList() {
    }

    public PlayList(@Constraints.Required String id, Set<VideoBasic> videos) {
        this.id = id;
        this.videos = videos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<VideoBasic> getVideos() {
        return videos;
    }

    public void setVideos(Set<VideoBasic> videos) {
        this.videos = videos;
    }

    @Override
    public String toString() {
        return "PlayList{" +
                "id='" + id + '\'' +
                ", videos=" + videos +
                '}';
    }
}
