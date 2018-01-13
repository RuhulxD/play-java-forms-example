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
    public boolean equals(Object obj) {
        if(obj != null && obj instanceof Category){
            Category cat = (Category) obj;
            return cat.name.equals(name) && cat.id.equals(id);
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        if(name!=null && id != null)
            return (int) (id.hashCode() + name.hashCode());
        return super.hashCode();
    }
}
