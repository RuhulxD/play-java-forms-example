package youtube;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;
import models.VideoBasic;
import play.libs.Json;
import utility.Utils;

import javax.inject.Singleton;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Singleton
public class YoutubePlayList {

    private YouTube youtube;
    private final String YOUTUBE_API;
    private final YouTube.PlaylistItems.List playlist;
    private final long NUMBER_OF_VIDEOS_RETURNED =40;

    public YoutubePlayList() throws IOException {
        this.YOUTUBE_API = Auth.getYoutubeAPIKey();
        youtube = Auth.getYoutube();
        playlist = youtube.playlistItems().list("id,snippet");
        playlist.setKey(YOUTUBE_API);
//        playlist.setPart("snippet");
//        playlist.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
        playlist.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
    }

    public PlaylistItemListResponse execute(String playistId, String pageToken) throws IOException {
        playlist.setPlaylistId(playistId).setPageToken(pageToken);
        return execute();
    }

    public PlaylistItemListResponse execute() throws IOException {
        return playlist.execute();
    }



    public List<VideoBasic> fetchAllListItems(String playListId, YoutubeParser parser) throws IOException {

        String nextToken = "";
        List<VideoBasic> basics = new ArrayList<>();
        Set<String>ids= new HashSet<>();

        // Call the API one or more times to retrieve all items in the
        // list. As long as the API response returns a nextPageToken,
        // there are still more items to retrieve.
        playlist.setPlaylistId(playListId);
        int episodeCounter=1;
        do {
            playlist.setPageToken(nextToken);
            PlaylistItemListResponse playlistItemResult = execute();
            for(PlaylistItem item: playlistItemResult.getItems()){
                VideoBasic basic = Utils.convertTo(item, parser);
                if(basic != null){
                    if(!ids.contains(basic.getyURL())) {
                        basic.setEpisode(episodeCounter++);
                        basics.add(basic);
                        ids.add(basic.getyURL());
                    }else{
                        System.err.println("###########DUPLICATE FOUND##############");
                        System.err.println(basic.getyURL());
                    }
                }
            }
            nextToken = playlistItemResult.getNextPageToken();
        } while (nextToken != null);
        return basics;
    }



    public static void main(String args[]){
        try {
            YoutubePlayList playList = new YoutubePlayList();
            YoutubeParserBuilder builder = new YoutubeParserBuilder();
            //Bangla Natok Houseful l Mithila, Mosharof Karim, Hasan Masud l Episode 02 I Drama & Telefilm
            builder.setActors("^.*?([Mm]ithila.*?ud)");
            builder.setEpisode("[Ee]pi.*?(\\d+)");
            builder.setName("^Bangla Natok (\\w+) l");

            List<VideoBasic> basics = playList.fetchAllListItems("PLsoqXr7gqLBxr9yKh-ezwlGBa8de8llNO", builder.createYoutubeParser());
            Utils.print(basics);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return;
    }

}
