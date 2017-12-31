package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import play.data.validation.Constraints;

import javax.persistence.Column;
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
    public String name;

    @Id
    @Constraints.Required
    @JsonProperty("Value")
    public String value;

    @Column(unique=true)
    public String generatedId;


    @Constraints.Required
    @JsonProperty("Type")
    public Integer type;

    @JsonProperty("Description")
    public String description;

    public Category() {
        super();
        this.generatedId = String.valueOf(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", generatedId='" + generatedId + '\'' +
                ", type=" + type +
                ", description='" + description + '\'' +
                '}';
    }
}
