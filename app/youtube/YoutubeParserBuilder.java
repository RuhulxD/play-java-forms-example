package youtube;

import java.util.HashMap;
import java.util.Map;

public class YoutubeParserBuilder {
    private Map<ParserType, BasicRegexParser> map;

    private YoutubeParserBuilder setMap(Map<ParserType, BasicRegexParser> map) {
        this.map = map;
        return this;
    }

    public YoutubeParserBuilder(){
        map = new HashMap<>();
    }

    public YoutubeParserBuilder setTitle(String regex){
        BasicRegexParser p = new BasicRegexParser(regex);
        this.map.put(ParserType.TITLE, p);
        return  this;
    }
    public YoutubeParserBuilder setName(String regex){
        BasicRegexParser p = new BasicRegexParser(regex);
        this.map.put(ParserType.NAME, p);
        return  this;
    }
    public YoutubeParserBuilder setSeason(String regex){
        BasicRegexParser p = new BasicRegexParser(regex);
        this.map.put(ParserType.SEASON, p);
        return  this;
    }
    public YoutubeParserBuilder setEpisode(String regex){
        BasicRegexParser p = new BasicRegexParser(regex);
        this.map.put(ParserType.EPISODE, p);
        return  this;
    }
    public YoutubeParserBuilder setActors(String regex){
        BasicRegexParser p = new BasicRegexParser(regex);
        this.map.put(ParserType.ACTORS, p);
        return  this;
    }
    public YoutubeParser createYoutubeParser() {
        return new YoutubeParser(map);
    }
}