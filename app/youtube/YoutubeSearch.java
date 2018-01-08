package youtube;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTube.Search.List;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import models.VideoBasic;
import utility.Utils;

import javax.inject.Singleton;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

@Singleton
public class YoutubeSearch {

    private YouTube youtube;
    private final List search;
    private final long NUMBER_OF_VIDEOS_RETURNED =40;

    public YoutubeSearch() throws IOException {
        this.youtube = Auth.getYoutube();
        search = youtube.search().list("id,snippet");
        search.setKey(Auth.getYoutubeAPIKey());
        search.setType("video");
        //search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
        search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
    }

    private SearchListResponse execute(String query, String channelId, String pageToken) throws IOException {
        search.setChannelId(channelId).setQ(query).setPageToken(pageToken);
        SearchListResponse response = execute();
        search.setChannelId(null).setQ(null).setPageToken(null);
        return response;
    }

    public java.util.List<VideoBasic> searchByQuery(String query, String channgelId, int totalVideos) throws IOException {
        java.util.List<VideoBasic> list = new ArrayList<>();
        int x=0;
        String nextPageToken="";
        while(x < totalVideos){

            int limit = totalVideos-x;
            if(limit<=0) break;
            search.setMaxResults((long) limit);
            SearchListResponse response = execute(query, channgelId, nextPageToken);
            java.util.List<VideoBasic> lists = getList(response);
            x+=list.size();
            list.addAll(lists);

            nextPageToken = search.getPageToken();
            if(nextPageToken ==null){
                break;
            }
            if(list.size()<1){
                break;
            }
        }
        return list;
    }
    public java.util.List<VideoBasic> createPlayList(String start, int totalEpisode, String endQuery, String channgelId) throws IOException {
        HashSet<String> set = new HashSet<>();
        java.util.List<VideoBasic> list = new ArrayList<>();
        int x = 1;
        String nextPageToken=null;
        while(x < totalEpisode){
            search.setMaxResults(1L);
            String query = start+" "+ x + " "+endQuery;
            SearchListResponse response = execute(query, channgelId, nextPageToken);
            java.util.List<VideoBasic> lists = getList(response);

            Utils.print(lists);
            if(!lists.isEmpty()){
                VideoBasic basic = lists.get(0);
                basic.setEpisode(x);
                if(!set.contains(basic.getyURL())){
                    set.add(basic.getyURL());
                    list.add(basic);
                }else{
                    System.err.println("################################");
                    System.err.println("############Duplicate found for "+ query+" ###################");
                }
            }
            x++;
        }
        return list;
    }

    public SearchListResponse execute() throws IOException {
        return search.execute();
    }

    public java.util.List<VideoBasic> searchInRelatedVideos(String videoId, int totalVideos) throws IOException {
        java.util.List<VideoBasic> list = new ArrayList<>();
        search.setRelatedToVideoId(videoId);
        int x=0;
        String nextPageToken="";
        while(x < totalVideos){
            int limit = totalVideos-x;
            if(limit<=0) break;
            search.setPageToken(nextPageToken);
            search.setMaxResults(Math.min(limit, NUMBER_OF_VIDEOS_RETURNED));
            SearchListResponse response = execute();
            java.util.List<VideoBasic> lists = getList(response);
            x+=list.size();
            list.addAll(lists);

            nextPageToken = search.getPageToken();
            if(nextPageToken ==null){
                break;
            }
            if(list.size()<1){
                break;
            }
        }
        return list;
    }




    public java.util.List<VideoBasic> getList(SearchListResponse response){
        java.util.List<SearchResult> searchResultList = response.getItems();
        java.util.List<VideoBasic> list = new ArrayList<>();
        if (searchResultList != null) {
            for(SearchResult rs: searchResultList){
                list.add(Utils.converTo(rs));
            }
        }
        return list;
    }
    public static void main(String args[]){
        try {
            YoutubeSearch search = new YoutubeSearch();
            java.util.List<VideoBasic> basics = search.createPlayList("Bangla Comedy/Funny Natok | Dramaserial - 420 ft. Mosharraf Karim, Tisha - Part", 35, "", "UC6-5LioFeO6MXxv9QPKhnlA");
            // /search.searchByQuery("420 Channel i | Mostofa Sarwar Farooki", "UC9nuJbEL-AMJLLqc2-ej8xQ", 5);

            Utils.print(basics);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }
}
