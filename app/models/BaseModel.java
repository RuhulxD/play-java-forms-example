package models;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseModel  extends AbstractJsonMapping{
    @Id
    public String id;

    public  BaseModel(){
        id = Long.toString(System.currentTimeMillis());
    }
}