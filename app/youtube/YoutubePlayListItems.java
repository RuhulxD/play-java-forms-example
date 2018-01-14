package youtube;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTube.PlaylistItems.List;
import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
import models.VideoBasic;
import utility.Utils;

import javax.inject.Singleton;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Singleton
public class YoutubePlayListItems {

    private YouTube youtube;
    private final String YOUTUBE_API;
    private final List playlist;
    private final long NUMBER_OF_VIDEOS_RETURNED =40;

    public YoutubePlayListItems() throws IOException {
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



    public java.util.List<VideoBasic> fetchAllListItems(String playListId, YoutubeParser parser) throws IOException {

        String nextToken = "";
        java.util.List<VideoBasic> basics = new ArrayList<>();
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
                VideoBasic basic = null;
                try {
                    basic = Utils.convertTo(item, parser);
                } catch (Exception e) {
                   // e.printStackTrace();
                }
                if(basic != null){
                    if(!ids.contains(basic.getyURL())) {
                        basic.setEpisode(episodeCounter++);
                        basics.add(basic);
                        ids.add(basic.getyURL());
                    }else{
//                        System.err.println("###########DUPLICATE FOUND##############");
//                        System.err.println(basic.getyURL());
                    }
                }else{
//                    System.err.println("#############null returned for "+ Utils.getString(item));
                }
            }
            nextToken = playlistItemResult.getNextPageToken();
        } while (nextToken != null);
        return basics;
    }

    public static void main(String args[]){
        try {
            YoutubePlayListItems playList = new YoutubePlayListItems();
            YoutubeParserBuilder builder = new YoutubeParserBuilder();
            //Bangla Natok Houseful l Mithila, Mosharof Karim, Hasan Masud l Episode 02 I Drama & Telefilm
            builder.setActors("^.*?([Mm]ithila.*?ud)");
            builder.setEpisode("[Ee]pi.*?(\\d+)");
            builder.setName("^Bangla Natok (\\w+) l");

            java.util.List<VideoBasic> basics = playList.fetchAllListItems("PLsoqXr7gqLBxr9yKh-ezwlGBa8de8llNO", builder.createYoutubeParser());
            Utils.print(basics);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return;
    }

}