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

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public class VideoBasic extends BaseModel implements Serializable {

    @Constraints.Required
    @JsonProperty("Title")
    public String title = "";

    @Constraints.Required
    @JsonProperty("Year")
    public String year = "";

    @JsonProperty("imdbID")
    public String imdbID = "";

    @Constraints.Required
    @JsonProperty("youtubeURL")
    public String yURL = "";


    @JsonProperty("Type")
    public String tp = "";

    @JsonProperty("Poster")
    public String poster = "";

    public  VideoBasic(){

    }
    public VideoBasic(@Constraints.Required String title, @Constraints.Required String year, String imdbID, @Constraints.Required String yURL, String poster) {
        this.title = title;
        this.year = year;
        this.imdbID = imdbID;
        this.yURL = yURL;
        this.poster = poster;
    }
}
