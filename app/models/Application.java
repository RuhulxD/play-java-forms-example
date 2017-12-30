package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="application")
public class Application implements Serializable {
    @Id
    public String name;

    public String apiKey;

    public String description;

}
