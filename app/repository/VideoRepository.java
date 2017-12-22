package repository;

import io.ebean.Ebean;
import io.ebean.EbeanServer;
import io.ebean.PagedList;
import io.ebean.Transaction;
import models.VideoFull;
import play.db.ebean.EbeanConfig;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class VideoRepository {

    private final EbeanServer ebeanServer;
    private final DatabaseExecutionContext executionContext;

    @Inject
    public VideoRepository(EbeanConfig ebeanConfig, DatabaseExecutionContext executionContext) {
        this.ebeanServer = Ebean.getServer(ebeanConfig.defaultServer());
        this.executionContext = executionContext;
    }


    public CompletionStage<PagedList<VideoFull>> page(int page, int pageSize, String sortBy, String order, String filter) {
        return supplyAsync(() -> {
             return pagedList(page, pageSize, sortBy, order, filter);
        } , executionContext);
    }

    public PagedList<VideoFull> pagedList(int page, int pageSize, String sortBy, String order, String filter) {
            return ebeanServer.find(VideoFull.class).where()
                    .ilike("title", "%" + filter + "%")
                    .orderBy(sortBy + " " + order)
                    //.fetch("video_full", "title")
                    .setFirstRow(page * pageSize)
                    .setMaxRows(pageSize)
                    .findPagedList();
    }

    public CompletionStage<Optional<VideoFull>> lookup(Long id) {
        return supplyAsync(() -> {
            return Optional.ofNullable(findById(id));
        }, executionContext);
    }

    public VideoFull findById(Long id){
        return ebeanServer.find(VideoFull.class).setId(id).findOne();
    }

    public VideoFull findFullById(Long id){
        return ebeanServer.find(VideoFull.class).setId(id).findOne();
    }


    public CompletionStage<Optional<Long>> update(Long id, VideoFull newVideoBasic) {

        return supplyAsync(() -> {
            Transaction txn = ebeanServer.beginTransaction();
            Optional<Long> value = Optional.empty();
            try {
                VideoFull savedVideo = findById(id);
                if (savedVideo != null) {
                    savedVideo.poster = newVideoBasic.poster;
                    savedVideo.imdbID = newVideoBasic.imdbID;
                    savedVideo.tp = newVideoBasic.tp;
                    savedVideo.title = newVideoBasic.title;
                    savedVideo.year = newVideoBasic.year;

                    savedVideo.update();
                    txn.commit();
                    value = Optional.of(id);
                }
            } finally {
                txn.end();
            }
            return value;
        }, executionContext);
    }

    public List<VideoFull> getDefaultList(){
        return pagedList(0, 10, "year", "asc", "").getList();
    }

    public CompletionStage<Optional<Long>>  delete(Long id) {
        return supplyAsync(() -> {
            try {
                final Optional<VideoFull> videoOptional = Optional.ofNullable(findById(id));
                videoOptional.ifPresent(c -> c.delete());
                return videoOptional.map(c -> c.id);
            } catch (Exception e) {
                return Optional.empty();
            }
        }, executionContext);
    }

//    public Long add(VideoFull videoBasic, boolean setId){
//
//        if(setId)
//            VideoFull.id = System.currentTimeMillis(); // not ideal, but it works
//        ebeanServer.insert(VideoFull);
//        return VideoFull.id;
//
//    }

    public Long add(VideoFull videoFull){
        videoFull.id = System.currentTimeMillis(); // not ideal, but it works
        ebeanServer.insert(videoFull);
        return videoFull.id;

    }

/*
    public CompletionStage<Long> insert(VideoFull videoBasic) {
        return supplyAsync(() -> {
            return add(videoBasic, true);
        }, executionContext);
    }*/

    public CompletionStage<Long> insert(VideoFull video) {
        return supplyAsync(() -> {
            return add(video);
        }, executionContext);
    }


}
