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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonProperty("Videos")
    public List<VideoBasic> videos;

    public PlayList(){
        super();
    }

    public PlayList(String playlistId, String title, List<VideoBasic> videList) {
        super();
        this.id = playlistId;
        this.title = title;
        this.videos = videList;
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
