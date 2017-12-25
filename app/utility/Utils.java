package utility;

import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.PlaylistItemSnippet;
import com.google.api.services.youtube.model.SearchResult;
import models.VideoBasic;
import models.VideoBasicBuilder;
import play.libs.Json;
import youtube.YoutubeParser;

import java.util.regex.Pattern;

public class Utils {

    public static VideoBasic converTo(SearchResult sr){
        VideoBasicBuilder bd = new VideoBasicBuilder();
        bd.setTitle(sr.getSnippet().getTitle());
        bd.setDescription(sr.getSnippet().getDescription());
        bd.setName(sr.getId().getVideoId());
        bd.setChannelId(sr.getSnippet().getChannelId());
        bd.setPoster(sr.getSnippet().getThumbnails().getDefault().getUrl());
        bd.setyURL(sr.getId().getVideoId());
        bd.setPublishedTime(sr.getSnippet().getPublishedAt().getValue());
        return bd.createVideoBasic();
    }

    public static VideoBasic convertTo(PlaylistItem item, YoutubeParser parser){
        VideoBasicBuilder builder = new VideoBasicBuilder();
        String title = item.getSnippet().getTitle();
        if(parser !=null) {
            try {
                builder.setActors(parser.getActors(title));
            } catch (Exception e) {
//            e.printStackTrace();
            }
            try {
                builder.setEpisode(parser.getEpisode(title));
            } catch (Exception e) {
//            e.printStackTrace();
            }
            try {
                builder.setName(parser.getName(title));
            } catch (Exception e) {
//            e.printStackTrace();
            }
            try {
                builder.setSeason(parser.getSeason(title));
            } catch (Exception e) {
//            e.printStackTrace();
            }
        }else{
            builder.setName(title);
        }

        builder.setPublishedTime(item.getSnippet().getPublishedAt().getValue());
        builder.setPoster(item.getSnippet().getThumbnails().getDefault().getUrl());
        builder.setDescription(item.getSnippet().getDescription());
        builder.setyURL(item.getSnippet().getResourceId().getVideoId());
        builder.setTitle(title);
        return builder.createVideoBasic();
    }
    public static void print(Object... obj){
        System.out.println(getString(obj));
    }
    public static String getString(Object... obj){
        return Json.prettyPrint(Json.toJson(obj));
    }
}
