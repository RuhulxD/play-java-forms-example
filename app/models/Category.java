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
    private String name;


    @Constraints.Required
    @JsonProperty("Value")
    private String value;

    @Constraints.Required
    @JsonProperty("Title")
    private String title;

    @Constraints.Required
    @JsonProperty("Type")
    private Integer type;

    @JsonProperty("Description")
    private String description;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
