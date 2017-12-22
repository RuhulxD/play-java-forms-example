/*
 *      Copyright (c) 2013-2016 Stuart Boston
 *
 *      This file is part of the OMDB API.
 *
 *      The OMDB API is free software: you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation, either version 3 of the License, or
 *      any later version.
 *
 *      The OMDB API is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *
 *      You should have received a copy of the GNU General Public License
 *      along with the OMDB API.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import java.io.Serializable;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@Entity
public class VideoFull extends VideoBasic implements Serializable {

    @JsonProperty("Rated")
    public String rated ;
    @JsonProperty("Released")
    public String released ;
    @JsonProperty("Runtime")
    public String runtime ;

    @Constraints.Required
    @JsonProperty("Genre")
    public String genre ;
    @JsonProperty("Director")
    public String director ;
    @JsonProperty("Writer")
    public String writer ;
    @JsonProperty("Actors")
    public String actors ;
    @JsonProperty("Plot")
    public String plot ;
    @JsonProperty("imdbRating")
    public String imdbRating ;
    @JsonProperty("imdbVotes")
    public String imdbVotes ;
    // Rotten Tomatoes fields
    @JsonProperty("tomatoMeter")
    public String tomatoMeter ;
    @JsonProperty("tomatoImage")
    public String tomatoImage ;
    @JsonProperty("tomatoRating")
    public String tomatoRating ;
    @JsonProperty("tomatoReviews")
    public String tomatoReviews ;
    @JsonProperty("tomatoFresh")
    public String tomatoFresh ;
    @JsonProperty("tomatoRotten")
    public String tomatoRotten ;
    @JsonProperty("tomatoConsensus")
    public String tomatoConsensus ;
    @JsonProperty("tomatoUserMeter")
    public String tomatoUserMeter ;
    @JsonProperty("tomatoUserRating")
    public String tomatoUserRating ;
    @JsonProperty("tomatoUserReviews")
    public String tomatoUserReviews ;
    @JsonProperty("tomatoURL")
    public String tomatoURL ;
    @JsonProperty("DVD")
    public String tomatoDvd ;
    @JsonProperty("BoxOffice")
    public String tomatoBoxOffice ;
    @JsonProperty("Production")
    public String tomatoProduction ;
    @JsonProperty("Website")
    public String tomatoWebsite ;
    @JsonProperty("Language")
    public String languages ;

    @JsonProperty("Country")
    public String countries ;


    @JsonProperty("Awards")
    public String awards ;
    @JsonProperty("Metascore")
    public int metascore = 0;
    @JsonProperty("Season")
    public Integer season;
    @JsonProperty("Episode")
    public Integer episode;

    public VideoFull() {
    }

    public VideoFull(@Constraints.Required String title, @Constraints.Required String year, String imdbID,
                     @Constraints.Required String type, String poster, String category, String rated, String released, String runtime,
                     @Constraints.Required String genre, String director, String writer, String actors, String plot,
                     String imdbRating, String imdbVotes, String tomatoMeter, String tomatoImage, String tomatoRating,
                     String tomatoReviews, String tomatoFresh, String tomatoRotten, String tomatoConsensus,
                     String tomatoUserMeter, String tomatoUserRating, String tomatoUserReviews, String tomatoURL,
                     String tomatoDvd, String tomatoBoxOffice, String tomatoProduction, String tomatoWebsite,
                     String languages, String countries, String awards, int metascore, Integer season, Integer episode) {
        super(title, year, imdbID, type, poster, category);
        this.rated = rated;
        this.released = released;
        this.runtime = runtime;
        this.genre = genre;
        this.director = director;
        this.writer = writer;
        this.actors = actors;
        this.plot = plot;
        this.imdbRating = imdbRating;
        this.imdbVotes = imdbVotes;
        this.tomatoMeter = tomatoMeter;
        this.tomatoImage = tomatoImage;
        this.tomatoRating = tomatoRating;
        this.tomatoReviews = tomatoReviews;
        this.tomatoFresh = tomatoFresh;
        this.tomatoRotten = tomatoRotten;
        this.tomatoConsensus = tomatoConsensus;
        this.tomatoUserMeter = tomatoUserMeter;
        this.tomatoUserRating = tomatoUserRating;
        this.tomatoUserReviews = tomatoUserReviews;
        this.tomatoURL = tomatoURL;
        this.tomatoDvd = tomatoDvd;
        this.tomatoBoxOffice = tomatoBoxOffice;
        this.tomatoProduction = tomatoProduction;
        this.tomatoWebsite = tomatoWebsite;
        this.languages = languages;
        this.countries = countries;
        this.awards = awards;
        this.metascore = metascore;
        this.season = season;
        this.episode = episode;
    }

//    @JsonSetter("Language")
//    public void setLanguageList(String languages) {
//        this.languages = Arrays.asList(languages.split(","));
//    }
//
//    @JsonSetter("Country")
//    public void setCountryList(String countries) {
//        this.countries = Arrays.asList(countries.split(","));
//    }
}
