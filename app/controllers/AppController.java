package controllers;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import database.BackendDao;
import database.CategoryDao;
import database.PlayListDao;
import javafx.util.Pair;
import models.*;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utility.Types;
import utility.Utils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class AppController  extends Controller{
    private final CategoryDao categoryDao;
    private final PlayListDao playListDao;
    private final BackendDao backendDao;

    @Inject
    public AppController(CategoryDao categoryDao, PlayListDao playListDao, BackendDao backendDao) {
        this.categoryDao = categoryDao;
        this.playListDao = playListDao;
        this.backendDao = backendDao;
    }

    public Result getCategories(String applicationID){
        List<Category> categories = categoryDao.getCategories(applicationID);
        return ok(Json.toJson(categories));
    }

    public Result getCategoriesWithList(String catName, int type, int start, int limit){
        List<Pair<Category, PlayList>> dto = new ArrayList<>();
        List<Category>categories = categoryDao.getCategories(catName, type,0, 100);
        for(Category cat: categories){
            if(cat.type == Types.PLAY_LIST.value()){
                PlayList list =playListDao.getPlayList(cat.value, start, limit);
                dto.add(new Pair<>(cat, list));
            }
        }
        return ok(Json.toJson(dto));
    }

    public Result getPlayList(String playlistid, int start, int limit){
        TyppedDTO<PlayList>dto = new TyppedDTO<>();
        dto.setData(playListDao.getPlayList(playlistid, start, limit));
        dto.setResponse(true);
        dto.setStatus(true);
        return ok(Json.toJson(dto));
    }
}
