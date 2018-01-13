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
    public String name;


    @Constraints.Required
    public String apiKey;


    @Constraints.Required
    public String description;
}
