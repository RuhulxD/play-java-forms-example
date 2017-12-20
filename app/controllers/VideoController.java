package controllers;

import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;


import static play.libs.Scala.asScala;

@Singleton
public class VideoController extends Controller{

    private final Form<VideoData> form;
    private final List<VideoData> videos;

    @Inject
    public VideoController(FormFactory formFactory) {
        this.form = formFactory.form(VideoData.class);
        this.videos = com.google.common.collect.Lists.newArrayList(
                new VideoDataBuilder().setTitle("adfasd")
                        .setActors("jumman,rajib")
                        .setGenre("action")
                .setType("adfdf")
                .setYear("2011")
                .setPoster("poster").setTags("movie, natok, drama").setYoutubeUrl("url").setTrailer("url").createVideoData()
        );
    }

    public Result index() {
        return ok(views.html.index.render());
    }

    public Result listVideos() {
        return ok(views.html.listVideos.render(asScala(videos), form));
    }

    public Result createVideos() {
        final Form<VideoData> boundForm = form.bindFromRequest();

        if (boundForm.hasErrors()) {
            play.Logger.ALogger logger = play.Logger.of(getClass());
            logger.error("errors = {}", boundForm.errors());
            return badRequest(views.html.listVideos.render(asScala(videos), boundForm));
        } else {
            VideoData data = boundForm.get();
            videos.add(data);
            flash("info", "Widget added!");
            return redirect(routes.VideoController.listVideos());
        }
    }
}
