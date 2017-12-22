package controllers;

import models.VideoBasic;
import models.VideoFull;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import repository.VideoRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.concurrent.CompletionStage;

import static play.libs.Scala.asScala;

@Singleton
public class VideoController extends Controller{

    private final Form<VideoFull> form;
    private final VideoRepository videoRepository;
    private HttpExecutionContext httpExecutionContext;

    @Inject
    public VideoController(FormFactory formFactory, VideoRepository videoRepository, HttpExecutionContext httpExecutionContext) {
        this.form = formFactory.form(VideoFull.class);
        this.videoRepository = videoRepository;
        this.httpExecutionContext = httpExecutionContext;
    }

    public Result index() {
        return ok(views.html.index.render());
    }

    public Result listVideos() {

        final List<VideoFull> videos = videoRepository.getDefaultList();
        return ok(views.html.listVideos.render(asScala(videos), form));
    }

    public Result pagedList(int page, String sortBy, String order, String filter) {
        // Run a db operation in another thread (using DatabaseExecutionContext)
        //return videoRepository.page(page, 10, sortBy, order, filter).thenApplyAsync(list -> {
            // This is the HTTP rendering thread context
            //return ok(Json.toJson(videoRepository.pagedList(page, 10, sortBy, order, filter)));
        //}, httpExecutionContext.current());
        return getVideos();
    }

    public Result getVideos(){
        return ok(Json.toJson(videoRepository.getDefaultList()));
    }



    public Result createVideos() {
        final Form<VideoFull> boundForm = form.bindFromRequest();

        if (boundForm.hasErrors()) {
            play.Logger.ALogger logger = play.Logger.of(getClass());
            logger.error("errors = {}", boundForm.errors());
            List<VideoFull> videos = videoRepository.getDefaultList();
            return badRequest(views.html.listVideos.render(asScala(videos), boundForm));
        } else {
            VideoFull data = boundForm.get();
            videoRepository.insert(data);
            flash("info", "Videos added!");
            return redirect(routes.VideoController.listVideos());
        }
    }
}
