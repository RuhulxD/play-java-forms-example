package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Category implements Serializable{

    @Id
    @Constraints.Required
    @JsonProperty("Name")
    String categoryName;

    @Id
    @Constraints.Required
    @JsonProperty("value")
    String CategoryValue;

    @Constraints.Required
    @JsonProperty("Type")
    Integer type;

    public Category() {
    }

    public Category(String name, String value, int type) {
        this.categoryName = name;
        this.CategoryValue = value;
        this.type = type;
    }
}
