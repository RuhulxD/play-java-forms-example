package models;

import play.data.validation.Constraints;

public class VideoBasicBuilder {
    private @Constraints.Required String title;
    private @Constraints.Required String year;
    private String imdbID;
    private @Constraints.Required String type;
    private String poster;
    private String category;
    private String yurl;

    public VideoBasicBuilder setTitle(@Constraints.Required String title) {
        this.title = title;
        return this;
    }

    public VideoBasicBuilder setYear(@Constraints.Required String year) {
        this.year = year;
        return this;
    }

    public VideoBasicBuilder setImdbID(String imdbID) {
        this.imdbID = imdbID;
        return this;
    }

    public VideoBasicBuilder setType(@Constraints.Required String type) {
        this.type = type;
        return this;
    }

    public VideoBasicBuilder setPoster(String poster) {
        this.poster = poster;
        return this;
    }
    public VideoBasicBuilder setCategory(String category) {
        this.category = category;
        return this;
    }

    public VideoBasic createVideoBasic() {
        return new VideoBasic(title, year, imdbID, yurl, poster, category);
    }
}