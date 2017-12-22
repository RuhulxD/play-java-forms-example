package models;

import play.data.validation.Constraints;

import java.util.List;

public class VideoFullBuilder {
    private @Constraints.Required String title;
    private @Constraints.Required String year;
    private @Constraints.Required String category;
    private String imdbID;
    private @Constraints.Required String type;
    private String poster;
    private String rated;
    private String released;
    private String runtime;
    private @Constraints.Required String genre;
    private String director;
    private String writer;
    private String actors;
    private String plot;
    private String imdbRating;
    private String imdbVotes;
    private String tomatoMeter;
    private String tomatoImage;
    private String tomatoRating;
    private String tomatoReviews;
    private String tomatoFresh;
    private String tomatoRotten;
    private String tomatoConsensus;
    private String tomatoUserMeter;
    private String tomatoUserRating;
    private String tomatoUserReviews;
    private String tomatoURL;
    private String tomatoDvd;
    private String tomatoBoxOffice;
    private String tomatoProduction;
    private String tomatoWebsite;
    private String languages;
    private String countries;
    private String awards;
    private int metascore;
    private Integer season;
    private Integer episode;

    public VideoFullBuilder setTitle(@Constraints.Required String title) {
        this.title = title;
        return this;
    }

    public VideoFullBuilder setYear(@Constraints.Required String year) {
        this.year = year;
        return this;
    }

    public VideoFullBuilder setImdbID(String imdbID) {
        this.imdbID = imdbID;
        return this;
    }

    public VideoFullBuilder setType(@Constraints.Required String type) {
        this.type = type;
        return this;
    }

    public VideoFullBuilder setPoster(String poster) {
        this.poster = poster;
        return this;
    }

    public VideoFullBuilder setRated(String rated) {
        this.rated = rated;
        return this;
    }

    public VideoFullBuilder setReleased(String released) {
        this.released = released;
        return this;
    }

    public VideoFullBuilder setRuntime(String runtime) {
        this.runtime = runtime;
        return this;
    }

    public VideoFullBuilder setGenre(@Constraints.Required String genre) {
        this.genre = genre;
        return this;
    }

    public VideoFullBuilder setDirector(String director) {
        this.director = director;
        return this;
    }

    public VideoFullBuilder setWriter(String writer) {
        this.writer = writer;
        return this;
    }

    public VideoFullBuilder setActors(String actors) {
        this.actors = actors;
        return this;
    }

    public VideoFullBuilder setPlot(String plot) {
        this.plot = plot;
        return this;
    }

    public VideoFullBuilder setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
        return this;
    }

    public VideoFullBuilder setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
        return this;
    }

    public VideoFullBuilder setTomatoMeter(String tomatoMeter) {
        this.tomatoMeter = tomatoMeter;
        return this;
    }

    public VideoFullBuilder setTomatoImage(String tomatoImage) {
        this.tomatoImage = tomatoImage;
        return this;
    }

    public VideoFullBuilder setTomatoRating(String tomatoRating) {
        this.tomatoRating = tomatoRating;
        return this;
    }

    public VideoFullBuilder setTomatoReviews(String tomatoReviews) {
        this.tomatoReviews = tomatoReviews;
        return this;
    }

    public VideoFullBuilder setTomatoFresh(String tomatoFresh) {
        this.tomatoFresh = tomatoFresh;
        return this;
    }

    public VideoFullBuilder setTomatoRotten(String tomatoRotten) {
        this.tomatoRotten = tomatoRotten;
        return this;
    }

    public VideoFullBuilder setTomatoConsensus(String tomatoConsensus) {
        this.tomatoConsensus = tomatoConsensus;
        return this;
    }

    public VideoFullBuilder setTomatoUserMeter(String tomatoUserMeter) {
        this.tomatoUserMeter = tomatoUserMeter;
        return this;
    }

    public VideoFullBuilder setTomatoUserRating(String tomatoUserRating) {
        this.tomatoUserRating = tomatoUserRating;
        return this;
    }

    public VideoFullBuilder setTomatoUserReviews(String tomatoUserReviews) {
        this.tomatoUserReviews = tomatoUserReviews;
        return this;
    }

    public VideoFullBuilder setTomatoURL(String tomatoURL) {
        this.tomatoURL = tomatoURL;
        return this;
    }

    public VideoFullBuilder setTomatoDvd(String tomatoDvd) {
        this.tomatoDvd = tomatoDvd;
        return this;
    }

    public VideoFullBuilder setTomatoBoxOffice(String tomatoBoxOffice) {
        this.tomatoBoxOffice = tomatoBoxOffice;
        return this;
    }

    public VideoFullBuilder setTomatoProduction(String tomatoProduction) {
        this.tomatoProduction = tomatoProduction;
        return this;
    }

    public VideoFullBuilder setTomatoWebsite(String tomatoWebsite) {
        this.tomatoWebsite = tomatoWebsite;
        return this;
    }

    public VideoFullBuilder setLanguages(String languages) {
        this.languages = languages;
        return this;
    }

    public VideoFullBuilder setCountries(String countries) {
        this.countries = countries;
        return this;
    }

    public VideoFullBuilder setAwards(String awards) {
        this.awards = awards;
        return this;
    }

    public VideoFullBuilder setMetascore(int metascore) {
        this.metascore = metascore;
        return this;
    }

    public VideoFullBuilder setSeason(Integer season) {
        this.season = season;
        return this;
    }

    public VideoFullBuilder setEpisode(Integer episode) {
        this.episode = episode;
        return this;
    }

    public VideoFull createVideoFull() {
        return new VideoFull(title, year, imdbID, type, poster, category, rated, released, runtime, genre, director, writer, actors, plot, imdbRating, imdbVotes, tomatoMeter, tomatoImage, tomatoRating, tomatoReviews, tomatoFresh, tomatoRotten, tomatoConsensus, tomatoUserMeter, tomatoUserRating, tomatoUserReviews, tomatoURL, tomatoDvd, tomatoBoxOffice, tomatoProduction, tomatoWebsite, languages, countries, awards, metascore, season, episode);
    }
}