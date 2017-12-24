package utility;

import com.google.api.services.youtube.model.SearchResult;
import models.VideoBasic;
import models.VideoBasicBuilder;

import java.util.regex.Pattern;

public class Utils {

    public static VideoBasic converToVideoBasic(SearchResult sr){
        VideoBasicBuilder bd = new VideoBasicBuilder();
        bd.setTitle(sr.getSnippet().getTitle());
        bd.setDescription(sr.getSnippet().getDescription());
        bd.setName(sr.getId().getVideoId());
        bd.setChannelId(sr.getSnippet().getChannelId());
        bd.setPoster(sr.getSnippet().getThumbnails().getMedium().getUrl());
        bd.setyURL("https://www.youtube.com/watch?v="+sr.getId().getVideoId());
        bd.setPublishedTime(sr.getSnippet().getPublishedAt().getValue());
        return bd.createVideoBasic();
    }

    public static int episodeParser(String str, String regex){
        Pattern compile = Pattern.compile(regex);
        if(str.matches(regex)){
            str.
        }

    }
    public static int episodeParser(String str, String regex){
    }
    public static int episodeParser(String str, String regex){
    }
}
