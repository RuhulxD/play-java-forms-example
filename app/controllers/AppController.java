package controllers;

import database.BackendDao;
import database.CategoryDao;
import database.PlayListDao;
import play.mvc.Controller;

import javax.inject.Inject;

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





}
