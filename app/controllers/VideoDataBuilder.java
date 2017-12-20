package controllers;

import play.data.validation.Constraints;

public class VideoDataBuilder {
    private @Constraints.Required String title;
    private @Constraints.Required String year;
    private @Constraints.Required String type;
    private String poster;
    private @Constraints.Required String genre;
    private String actors;
    private @Constraints.Required String tags;
    private @Constraints.Required String youtubeUrl;
    private @Constraints.Required String trailer;

    public VideoDataBuilder setTitle(@Constraints.Required String title) {
        this.title = title;
        return this;
    }

    public VideoDataBuilder setYear(@Constraints.Required String year) {
        this.year = year;
        return this;
    }

    public VideoDataBuilder setType(@Constraints.Required String type) {
        this.type = type;
        return this;
    }

    public VideoDataBuilder setPoster(String poster) {
        this.poster = poster;
        return this;
    }

    public VideoDataBuilder setGenre(@Constraints.Required String genre) {
        this.genre = genre;
        return this;
    }

    public VideoDataBuilder setActors(String actors) {
        this.actors = actors;
        return this;
    }

    public VideoDataBuilder setTags(@Constraints.Required String tags) {
        this.tags = tags;
        return this;
    }

    public VideoDataBuilder setYoutubeUrl(@Constraints.Required String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
        return this;
    }

    public VideoDataBuilder setTrailer(@Constraints.Required String trailer) {
        this.trailer = trailer;
        return this;
    }

    public VideoData createVideoData() {
        return new VideoData(title, year, type, poster, genre, actors, tags, youtubeUrl, trailer);
    }
}