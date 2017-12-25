package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import database.BackendDao;
import models.VideoBasic;
import models.VideoBasicBuilder;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.libs.Scala;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Map;
import java.util.Random;

@Singleton
public class VideoJPAController extends Controller {

    private final BackendDao backendDao;
    Random random ;
    private final Form<VideoBasic> form;

    @Inject
    public VideoJPAController(BackendDao backendDao, FormFactory formFactory){
        this.backendDao = backendDao;
        random = new Random(System.currentTimeMillis());
        form = formFactory.form(VideoBasic.class);
    }

    public  Result getVideoList(){
        return ok(ToJson(backendDao.selectAllVideoBasics()));
    }

    public Result index(){
        return ok(views.html.index.render());
    }

    public Result addVideo(){
        String[] actros = {"mosharrof " +" korim ", "opi korim", "abul"};
        String[] category = {"bangla hasir natok " ," eid natok ", "romantic", "abul"};
        VideoBasic full = new VideoBasicBuilder().createVideoBasic();
        full.setType(category[random.nextInt(category.length)]);
        full.setActors(actros[random.nextInt(category.length)]);
        full.setDescription("description"+random.nextLong());
        full.setCategoryId(random.nextLong());
        full.setyURL("yers");
        String str = Boolean.toString(backendDao.addOneVideoBasic(full));
        return ok(str);
    }

    public Result createVideo(){
        final Form<VideoBasic> boundForm = form.bindFromRequest();

        if (boundForm.hasErrors()) {
            play.Logger.ALogger logger = play.Logger.of(getClass());
            logger.error("errors = {}", boundForm.errors());
            return badRequest(views.html.createVideos.render(boundForm));
        } else {
            VideoBasic data = boundForm.get();
            backendDao.addOneVideoBasic(data);
            flash("info", "Video added! video id =" + data.getyURL());
            return ok(views.html.createVideos.render(boundForm));
        }
    }
    public Result viewVideoList(){
       return ok(views.html.listVideos.render(Scala.asScala(backendDao.selectAllVideoBasics())));
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
