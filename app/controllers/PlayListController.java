package controllers;

import database.PlayListDao;
import models.PlayList;
import models.PlayListBuilder;
import models.VideoBasic;
import play.mvc.Controller;
import play.mvc.Result;
import utility.Utils;
import youtube.YoutubePlayListItems;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class PlayListController extends Controller {
    private final PlayListDao dao;
    private final YoutubePlayListItems youtube;

    @Inject
    public PlayListController(PlayListDao dao, YoutubePlayListItems youtube){
        this.dao = dao;
        this.youtube = youtube;
    }

    public Result createPlayList(String playlistId){
        try {
            List<VideoBasic> videList = new ArrayList<>(youtube.fetchAllListItems(playlistId, null));
            PlayList playList = new PlayListBuilder().setId(playlistId).setTitle("asdfasdf").setVideos(videList).createPlayList();
            boolean b = dao.addToPlayList(playList);
            return response(b);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return response(false);
    }

    public Result getPlayList(String id){
        dao.getPlayLista();
        PlayList playList = dao.getPlayList(id, 0, 10);
        if(playList==null){
            return response(false);
        }
        return ok(Utils.getString(playList));
    }

    public Result getPlayLista(){
        return ok(Utils.getString(dao.getPlayLista()));
    }


    public Result response(boolean response){
        return ok(""+response);
    }



}
