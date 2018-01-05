package utility;

import com.google.api.services.youtube.model.Playlist;
import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.PlaylistItemSnippet;
import com.google.api.services.youtube.model.SearchResult;
import models.*;
import play.libs.Json;
import youtube.YoutubeParser;

import javax.persistence.Query;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
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
        builder.setChannelId(item.getSnippet().getChannelId());
        builder.setTitle(title);
        return builder.createVideoBasic();
    }
    public static void print(Object... obj){
        System.out.println(getString(obj));
    }
    public static String getString(Object... obj){
        return Json.prettyPrint(Json.toJson(obj));
    }

    public List getResult(Query q, Integer start, Integer limit, Object... parameter){
        if(parameter != null || parameter.length >0){
            for(int i=1; i<=parameter.length; i++){
                q.setParameter(i, parameter[i]);
            }
        }
        if(start != null){
            q.setFirstResult(start);
        }
        if(limit != null){
            q.setMaxResults(limit);
        }
        return q.getResultList();
    }

    public static String buildTable(List list){
        if(list == null || list.isEmpty()){
            return "<h1>No Data </h1>";
        }
        StringBuilder builder = new StringBuilder().append("<table>").append(buildHeader(list.get(0)));
        for(Object obj: list){
            builder.append(buildRow(obj));
        }
        return builder.append("</table>").toString();
    }


    public static String buildHeader( Object obj){
        StringBuilder builder= new StringBuilder().append("<thead>").append("<tr>");
        for(String str: getAllFields(obj)){
            builder.append("<th>").append(str).append("</th>");
        }
        builder.append("</tr></thead>");
        return builder.toString();
    }
    public static String buildRow( Object obj){
        StringBuilder builder= new StringBuilder().append("<tbody><tr>");
        for(String str: getValues(obj)){
            builder.append("<td>").append(str).append("</td>");
        }
        builder.append("</tr></tbody>");
        return builder.toString();
    }

    public static List<String> getValues(Object obj){
        List<String>strings = new ArrayList<>();
        for (Class<?> c = obj.getClass(); c != null; c = c.getSuperclass())
        {
            Field[] fields = c.getDeclaredFields();
            for (Field classField : fields) {
                try {
                    Object o = classField.get(obj);
                    strings.add(o == null ? "null" : o.toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return strings;
    }
    public static  List<String> getAllFields(Object obj){
        List<String>strings = new ArrayList<>();
        for (Class<?> c = obj.getClass(); c != null; c = c.getSuperclass())
        {
            Field[] fields = c.getDeclaredFields();
            for (Field classField : fields)
            {
                strings.add(classField.getName().toString());
            }
        }
//        for(Field field: obj.getClass().getDeclaredFields()){
//            strings.add(field.getName());
//        }
        return strings;
    }
    public static void main(String... args){
        Category category = new Category();
        category.name ="sadf";
        category.id="23213";

        System.out.println(buildHeader(category));
        System.out.println(buildRow(category));
    }

    public static PlayList converTo(Playlist item) {
        if(item == null){
            return null;
        }
        return new PlayListBuilder().setId(item.getId()).setThumb(item.getSnippet().getThumbnails().getHigh().getUrl()).setTitle(item.getSnippet().getTitle()).createPlayList();
    }
}
