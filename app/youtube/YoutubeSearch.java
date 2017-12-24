package youtube;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.typesafe.config.ConfigFactory;
import models.VideoBasic;
import utility.Utils;

import javax.inject.Singleton;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class YoutubeSearch {

    private YouTube youtube;
    private final String YOUTUBE_API;
    private final YouTube.Search.List search;
    private final long NUMBER_OF_VIDEOS_RETURNED =40;

    public YoutubeSearch() throws IOException {
        this.YOUTUBE_API = ConfigFactory.load().getString("youtube.apikey");
        youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, new HttpRequestInitializer() {
            public void initialize(HttpRequest request) throws IOException {
            }
        }).setApplicationName("ageless-aleph-170615").build();
        search = youtube.search().list("id,snippet");
        search.setKey(YOUTUBE_API);
        search.setType("video");
        search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
        search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
    }

    public SearchListResponse execute(String query, String channelId, String pageToken) throws IOException {
        search.setChannelId(channelId).setQ(query).setPageToken(pageToken);
        return search.execute();
    }

    public List<VideoBasic> searchInYoutube(String query, String channelId, String pageToken) throws IOException {
        SearchListResponse result = execute(query, channelId, pageToken);
        List<SearchResult> searchResultList = result.getItems();
        List<VideoBasic> list = new ArrayList<>();
        if (searchResultList != null) {
            for(SearchResult rs: searchResultList){
                list.add(Utils.converToVideoBasic(rs));
            }
        }
        return list;
    }
}
