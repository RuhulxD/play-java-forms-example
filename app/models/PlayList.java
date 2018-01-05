package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "playlist")
public class PlayList extends BaseModel {


    @Constraints.Required
    @JsonProperty("Title")
    public String title;

    @JsonProperty("Thumb")
    public String thumb;
    @JsonProperty("thumb1")
    public String thumb1;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonProperty("Videos")
    public List<VideoBasic> videos;

    public PlayList(){
        super();
    }

    public PlayList(String id, @Constraints.Required String title, String thumb, String thumb1, List<VideoBasic> videos) {
        super();
        this.title = title;
        this.thumb = thumb;
        this.thumb1 = thumb1;
        this.videos = videos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<VideoBasic> getVideos() {
        return videos;
    }

    public void setVideos(List<VideoBasic> videos) {
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
