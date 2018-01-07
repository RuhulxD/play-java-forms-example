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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ser.Serializers;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "videos",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"yURL"})
        }
)
public class VideoBasic implements Serializable {

    @Id
    @Constraints.Required
    @JsonProperty("youtubeURL")
    public String yURL;


    @JsonProperty("Category")
    @ManyToOne
    public PlayList category;

    @Constraints.Required
    @JsonProperty("catId")
    public Long categoryId;

    @Constraints.Required
    @JsonProperty("Title")
    public String title;


    @Constraints.Required
    @JsonProperty("PublishedTime")
    public Long publishedTime;


    @Constraints.Required
    @JsonProperty("ChannelId")
    public String channelId;

    @Constraints.Required
    @JsonProperty("Description")
    public String description;

    @Constraints.Required
    @JsonProperty("Actors")
    public String actors;

    @Constraints.Required
    @JsonProperty("tags")
    public String tags;

    @Constraints.Required
    @JsonProperty("genre")
    public String genre;


    @Constraints.Required
    @JsonProperty("Year")
    public String year;

    @JsonProperty("imdbID")
    public String imdbID;


    @JsonProperty("ParserType")
    public String type;


    @JsonProperty("Poster")
    public String poster;

    @JsonProperty("Region")
    public String region;

    @JsonProperty("Episode")
    public Integer episode;

    @JsonProperty("Season")
    public Integer season;

    @JsonProperty("Name")
    public String name;


    public VideoBasic() {
    }

    public VideoBasic(@Constraints.Required String yURL, PlayList category, @Constraints.Required Long categoryId, @Constraints.Required String title, @Constraints.Required Long publishedTime, @Constraints.Required String channelId, @Constraints.Required String description, @Constraints.Required String actors, @Constraints.Required String tags, @Constraints.Required String genre, @Constraints.Required String year, String imdbID, String type, String poster, String region, Integer episode, Integer season, String name) {
        this.yURL = yURL;
        this.category = category;
        this.categoryId = categoryId;
        this.title = title;
        this.publishedTime = publishedTime;
        this.channelId = channelId;
        this.description = description;
        this.actors = actors;
        this.tags = tags;
        this.genre = genre;
        this.year = year;
        this.imdbID = imdbID;
        this.type = type;
        this.poster = poster;
        this.region = region;
        this.episode = episode;
        this.season = season;
        this.name = name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPublishedTime() {
        return publishedTime;
    }

    public void setPublishedTime(Long publishedTime) {
        this.publishedTime = publishedTime;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getyURL() {
        return yURL;
    }

    public void setyURL(String yURL) {
        this.yURL = yURL;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PlayList getCategory() {
        return category;
    }

    public void setCategory(PlayList category) {
        this.category = category;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getEpisode() {
        return episode;
    }

    public void setEpisode(Integer episode) {
        this.episode = episode;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "VideoBasic{" +
                "yURL='" + yURL + '\'' +
                ", title='" + title + '\'' +
                ", publishedTime=" + publishedTime +
                ", poster='" + poster + '\'' +
                ", region='" + region + '\'' +
                ", episode=" + episode +
                '}';
    }
}
