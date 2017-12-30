package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "category")
public class Category implements Serializable {

    @Id
    @Constraints.Required
    @JsonProperty("Name")
    String name;

    @Id
    @Constraints.Required
    @JsonProperty("Value")
    String value;

    @Constraints.Required
    @JsonProperty("Type")
    Integer type;

    @JsonProperty("Description")
    String description;

    public Category() {
    }

    public Category(String name, String value, int type) {
        this.name = name;
        this.value = value;
        this.type = type;
    }

    public Category(String name, String value, int type, String description) {
        this.name = name;
        this.value = value;
        this.type = type;
        this.description = description;
    }
}
