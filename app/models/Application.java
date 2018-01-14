package models;

import play.data.validation.Constraints;

import javax.jdo.annotations.Unique;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="application")
public class Application extends BaseModel implements Serializable {

    @Unique
    @Constraints.Required
    private String name;


    @Constraints.Required
    private String apiKey;


    @Constraints.Required
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
