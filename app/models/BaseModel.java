package models;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseModel  extends AbstractJsonMapping{
    @Id
    public String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public  BaseModel(){
        id = Long.toString(System.currentTimeMillis());
    }
}