package models;

import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="application")
public class Application implements Serializable {
    @Id
    @Constraints.Required
    public String name;


    @Constraints.Required
    public String apiKey;


    @Constraints.Required
    public String description;

    @Override
    public String toString() {
        return "Application{" +
                "name='" + name + '\'' +
                ", apiKey='" + apiKey + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
