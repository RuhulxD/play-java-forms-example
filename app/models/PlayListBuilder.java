package models;

import play.data.validation.Constraints;

import java.util.List;

public class PlayListBuilder {
    private String id;
    private @Constraints.Required String title;
    private String thumb;
    private String thumb1;
    private int total;
    private List<VideoBasic> videos;

    public PlayListBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public PlayListBuilder setTitle(@Constraints.Required String title) {
        this.title = title;
        return this;
    }

    public PlayListBuilder setThumb(String thumb) {
        this.thumb = thumb;
        return this;
    }

    public PlayListBuilder setThumb1(String thumb1) {
        this.thumb1 = thumb1;
        return this;
    }

    public PlayListBuilder setVideos(List<VideoBasic> videos) {
        this.videos = videos;
        return this;
    }

    public PlayListBuilder setTotal(int total) {
        this.total = total;
        return this;
    }



    public PlayList createPlayList() {
        return new PlayList(id, title, thumb, thumb1, videos, total);
    }
}