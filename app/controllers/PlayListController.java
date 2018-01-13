package controllers;

import database.PlayListDao;
import models.Application;
import models.PlayList;
import models.PlayListBuilder;
import models.VideoBasic;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.libs.Scala;
import play.mvc.Controller;
import play.mvc.Result;
import utility.Utils;
import youtube.YoutubeChannelPlayList;
import youtube.YoutubePlayListItems;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.text.View;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Singleton
public class PlayListController extends Controller {
    private final PlayListDao dao;
    private final YoutubePlayListItems youtube;
    private final YoutubeChannelPlayList channelPlayList;
    private final Form<PlayList> forms;
    private final FormFactory factory;

    @Inject
    public PlayListController(PlayListDao dao, YoutubePlayListItems youtube, YoutubeChannelPlayList channelPlayList, FormFactory formFactory) {
        this.dao = dao;
        this.youtube = youtube;
        this.channelPlayList = channelPlayList;
        this.forms = formFactory.form(PlayList.class);
        this.factory = formFactory;

    }

    public Result createPlayList(String playlistId) {
        return createPlayList(playlistId, false);
    }

    public Result createPlayList(String playlistId, boolean withVideos) {
        try {
            PlayList playList = dao.getPlayListDetails(playlistId);
            if (withVideos) {
                List<VideoBasic> videList = new ArrayList<>(youtube.fetchAllListItems(playlistId, null));
                playList.setVideos(videList);
            }
            boolean b = dao.addToPlayList(playList);
            return response(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response(false);
    }

    public Result getPlayList(String id) {
        PlayList playList = dao.getPlayListDetails(id);
        if (playList == null) {
            return response(false);
        }
        return ok(Json.toJson(playList));
    }
    public Result getPlayLists(int start, int limit) {
        List<PlayList> playLists = dao.getPlayLists(start, limit);
        Utils.print(playLists);
        return ok(views.html.listPlayList.render(Scala.asScala(playLists)));
    }

    public Result getPlayList(String id, int start, int limit) {
        PlayList playList = dao.getPlayList(id, start, limit);
        if (playList == null) {
            return response(false);
        }
        return ok(Json.toJson(playList));
    }

    private List<PlayList> fetchAndBuildPlaylist(String channelId) {
        List<PlayList> playLists = Collections.emptyList();
        try {
             playLists = channelPlayList.fetchAllPlayList(channelId, null);
            for (PlayList list : playLists) {
                List<VideoBasic> videos = youtube.fetchAllListItems(list.id, null);
                list.setVideos(videos);
            }
            dao.addToPlayList(playLists);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        }
        return playLists;
    }

    public Result createPlayListForm(){
        DynamicForm requestData = factory.form().bindFromRequest();
        String channelId = requestData.get("channel");
        if(channelId ==null || channelId.isEmpty() || requestData.hasErrors()) {
            play.Logger.ALogger logger = play.Logger.of(getClass());
            logger.error("errors = {}", requestData.errors());
            return badRequest(views.html.createPlaylist.render("PlayList", requestData));
        } else {
            List<PlayList> playLists = Collections.emptyList();
            try {
                playLists = fetchAndBuildPlaylist(channelId);
                flash("info", "Apps added! ChannelId details =" + channelId.toString());
            }catch (Exception ex){
                flash("info", "Failed! App details =" + ex.getMessage());
            }
            return ok(views.html.createPlaylist.render("playList", requestData));
        }
    }


    public Result updatePlayList(String id){
        Form<PlayList> form = forms.bindFromRequest();
        PlayList list = dao.getPlayListDetails(id);
        if(list ==null || form.hasErrors()) {
            if(list!=null){
                form.fill(list);
            }
            play.Logger.ALogger logger = play.Logger.of(getClass());
            logger.error("errors = {}", form.errors());
            return badRequest(views.html.updatePlaylist.render("PlayList", form, id));
        } else {
            PlayList list1 = form.get();
            form.fill(list);
            try {
                dao.addToPlayList(list);
                flash("info", "playlist updated! PlaylistId  =" + list.id);
            }catch (Exception ex){
                flash("info", "Failed! playlist details =" + ex.getMessage());
            }
            return ok(views.html.updatePlaylist.render("playList", form, id));
        }
    }




    public Result deletePlayList(String id) {
        return ok("" + dao.deletePlayList(id));
    }

    public Result deletePlayList(String id, String videoId) {
        return ok("" + dao.deleteFromPlayList(id, videoId));
    }

    public Result response(boolean response) {
        return ok("" + response);
    }
}
