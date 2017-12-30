package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "playlist")
public class PlayList {

    @Id
    @Constraints.Required
    @JsonProperty("Id")
    String id;

    @Constraints.Required
    @JsonProperty("Title")
    String title;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonProperty("Videos")
    Set<VideoBasic> videos;

    public PlayList() {
    }

    public PlayList(@Constraints.Required String id, @Constraints.Required String title, Set<VideoBasic> videos) {
        this.id = id;
        this.videos = videos;
        this.title = title;
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
