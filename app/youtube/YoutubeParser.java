package youtube;

import java.util.Map;

public class YoutubeParser implements  Parser{
    private final Map<ParserType, BasicRegexParser> map;

    YoutubeParser(Map<ParserType, BasicRegexParser>map){
        this.map = map;
    }

    @Override
    public int getEpisode(String str) throws Exception {
        String ep = getDefault(ParserType.EPISODE, str);
        if(ep!=null){
            return Integer.parseInt(ep);
        }
        return -1;
    }

    @Override
    public String getName(String str) throws Exception {
        return getDefault(ParserType.NAME, str);
    }

    @Override
    public int getSeason(String str) throws Exception {
        String season = getDefault(ParserType.SEASON, str);
        if(season!=null){
            return Integer.parseInt(season);
        }
        return -1;
    }

    @Override
    public String getActors(String str) throws Exception {
        return getDefault(ParserType.ACTORS, str);
    }

    @Override
    public String getTitle(String str) throws Exception {
        return getDefault(ParserType.TITLE, str);
    }

    private String getDefault(ParserType type, String str) throws Exception {
        BasicRegexParser p = map.get(type);
        if(p!=null){
            return p.parse(str);
        }
        return null;
    }
}
