package controllers;

import db.BackendDao;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class VideoJPAController extends Controller {

    private final BackendDao backendDao;

    @Inject
    public VideoJPAController(BackendDao backendDao){
        this.backendDao = backendDao;
    }

    public  Result getVideoList(){
        return ok(Json.toJson(backendDao.selectAllVideoFulls()));
    }
}
