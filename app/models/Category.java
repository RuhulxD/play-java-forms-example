package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "category",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name", "value"})
        }
)
public class Category extends BaseModel implements Serializable {

    @Id
    @Constraints.Required
    @JsonProperty("Name")
    public String name;


    @Constraints.Required
    @JsonProperty("Value")
    public String value;

    @Constraints.Required
    @JsonProperty("Title")
    public String title;

    @Constraints.Required
    @JsonProperty("Type")
    public Integer type;

    @JsonProperty("Description")
    public String description;

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
