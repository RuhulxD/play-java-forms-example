package models;

import play.data.validation.Constraints;

public class VideoBasicBuilder {
    private @Constraints.Required String yURL;
    private PlayList category;
    private @Constraints.Required Long categoryId;
    private @Constraints.Required String title;
    private @Constraints.Required Long publishedTime;
    private @Constraints.Required String channelId;
    private @Constraints.Required String description;
    private @Constraints.Required String actors;
    private @Constraints.Required String tags;
    private @Constraints.Required String genre;
    private @Constraints.Required String year;
    private String imdbID;
    private String type;
    private String poster;
    private String region;
    private Integer episode;
    private Integer season;
    private String name;

    public VideoBasicBuilder setyURL(@Constraints.Required String yURL) {
        this.yURL = yURL;
        return this;
    }

    public VideoBasicBuilder setCategory(PlayList category) {
        this.category = category;
        return this;
    }

    public VideoBasicBuilder setCategoryId(@Constraints.Required Long categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public VideoBasicBuilder setTitle(@Constraints.Required String title) {
        this.title = title;
        return this;
    }

    public VideoBasicBuilder setPublishedTime(@Constraints.Required Long publishedTime) {
        this.publishedTime = publishedTime;
        return this;
    }

    public VideoBasicBuilder setChannelId(@Constraints.Required String channelId) {
        this.channelId = channelId;
        return this;
    }

    public VideoBasicBuilder setDescription(@Constraints.Required String description) {
        this.description = description;
        return this;
    }

    public VideoBasicBuilder setActors(@Constraints.Required String actors) {
        this.actors = actors;
        return this;
    }

    public VideoBasicBuilder setTags(@Constraints.Required String tags) {
        this.tags = tags;
        return this;
    }

    public VideoBasicBuilder setGenre(@Constraints.Required String genre) {
        this.genre = genre;
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

    public VideoBasicBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public VideoBasicBuilder setPoster(String poster) {
        this.poster = poster;
        return this;
    }

    public VideoBasicBuilder setRegion(String region) {
        this.region = region;
        return this;
    }

    public VideoBasicBuilder setEpisode(Integer episode) {
        this.episode = episode;
        return this;
    }

    public VideoBasicBuilder setSeason(Integer season) {
        this.season = season;
        return this;
    }

    public VideoBasicBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public VideoBasic createVideoBasic() {
        return new VideoBasic(yURL, category, categoryId, title, publishedTime, channelId, description, actors, tags, genre, year, imdbID, type, poster, region, episode, season, name);
    }
}