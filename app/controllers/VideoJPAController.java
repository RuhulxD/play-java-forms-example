package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import db.BackendDao;
import models.VideoFull;
import models.VideoFullBuilder;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.libs.Scala;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.*;

@Singleton
public class VideoJPAController extends Controller {

    private final BackendDao backendDao;
    Random random ;
    private final Form<VideoFull> form;

    @Inject
    public VideoJPAController(BackendDao backendDao, FormFactory formFactory){
        this.backendDao = backendDao;
        random = new Random(System.currentTimeMillis());
        form = formFactory.form(VideoFull.class);
    }

    public  Result getVideoList(){
        return ok(ToJson(backendDao.selectAllVideoFulls()));
    }

    public Result addVideo(){
        String[] actros = {"mosharrof " +" korim ", "opi korim", "abul"};
        VideoFull full = new VideoFullBuilder().setImdbID("ok "+random.nextLong()).setActors(actros[random.nextInt(actros.length)]).createVideoFull();
        String str = new Boolean(backendDao.addOneVideoFull(full)).toString();
        return ok(str);
    }

    public Result createVideo(){
        final Form<VideoFull> boundForm = form.bindFromRequest();

        if (boundForm.hasErrors()) {
            play.Logger.ALogger logger = play.Logger.of(getClass());
            logger.error("errors = {}", boundForm.errors());
            return badRequest(views.html.createVideos.render(boundForm));
        } else {
            VideoFull data = boundForm.get();
            backendDao.addOneVideoFull(data);
            flash("info", "Video added! video id =" + data.id);
            return ok(views.html.createVideos.render(boundForm));
        }
    }
    public Result viewVideoList(){
       return ok(views.html.listVideos.render(Scala.asScala(backendDao.selectAllVideoFulls())));
    }

    public Result search(Map<String, String> searchMapper){
        return ok(ToJson(backendDao.search(searchMapper)));
    }

    public Result search(String query){
        return ok(ToJson(backendDao.search(query)));
    }


    private String ToJson(JsonNode obj){
        return Json.prettyPrint(obj);
    }
    private String  ToJson(Object... obj){
        return ToJson(Json.toJson(obj));
    }

}
