package youtube;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Playlist;
import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
import com.google.api.services.youtube.model.PlaylistListResponse;
import models.VideoBasic;
import utility.Utils;

import javax.inject.Singleton;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Singleton
public class YoutubeChannelPlayList {

    private YouTube youtube;
    private final String YOUTUBE_API;
    private final YouTube.Playlists.List playlist;
    private final long NUMBER_OF_VIDEOS_RETURNED =40;

    public YoutubeChannelPlayList() throws IOException {
        this.YOUTUBE_API = Auth.getYoutubeAPIKey();
        youtube = Auth.getYoutube();
        playlist = youtube.playlists().list("id,snippet");
        playlist.setKey(YOUTUBE_API);
//        playlist.setPart("snippet");
//        playlist.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
        playlist.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
    }

    public PlaylistListResponse execute(String channelId, String pageToken) throws IOException {
        playlist.setChannelId(channelId).setPageToken(pageToken);
        return execute();
    }

    public PlaylistListResponse execute() throws IOException {
        return playlist.execute();
    }



    public String fetchAllListItems(String channelId, YoutubeParser parser) throws IOException {

        String nextToken = "";
        List<Playlist> basics = new ArrayList<>();
        Set<String>ids= new HashSet<>();

        // Call the API one or more times to retrieve all items in the
        // list. As long as the API response returns a nextPageToken,
        // there are still more items to retrieve.
        playlist.setChannelId(channelId);
        int episodeCounter=1;
        do {
            playlist.setPageToken(nextToken);
            PlaylistListResponse playlistItemResult = execute();
            for(Playlist item: playlistItemResult.getItems()){
                basics.add(item);
            }
            nextToken = playlistItemResult.getNextPageToken();
        } while (nextToken != null);
        return Utils.getString(basics);
    }

    public static void main(String args[]){
        try {
            YoutubeChannelPlayList playList = new YoutubeChannelPlayList();
            YoutubeParserBuilder builder = new YoutubeParserBuilder();
            //Bangla Natok Houseful l Mithila, Mosharof Karim, Hasan Masud l Episode 02 I Drama & Telefilm
            builder.setActors("^.*?([Mm]ithila.*?ud)");
            builder.setEpisode("[Ee]pi.*?(\\d+)");
            builder.setName("^Bangla Natok (\\w+) l");

            String  basics = playList.fetchAllListItems("UCFy4dUqTg9grMVt9ZhTfisA", builder.createYoutubeParser());
            Utils.print(basics);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return;
    }

}
