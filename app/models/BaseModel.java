package models;

import javafx.scene.SubScene;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseModel {
    @Id
    public Long id;

    public  BaseModel(){
        id = System.currentTimeMillis();
    }
}