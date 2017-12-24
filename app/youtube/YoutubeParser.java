package youtube;

import models.VideoBasic;

import java.util.regex.Pattern;

public class YoutubeParser implements Parser{
    VideoBasic basic;
    String regexStr;
    Pattern p;

    public YoutubeParser(String episodeRegex, String titleParser, String ) {
        this(new VideoBasic());
        p = Pattern.compile(regex);
    }

    public YoutubeParser(VideoBasic basic, String regex) {
        this.basic = basic;
        this.regexStr = regex;
    }

    @Override
    public int getEpisode() {
        return 0;
    }

    @Override
    public int getSeason() {
        return 0;
    }

    @Override
    public int getTitle() {
        return 0;
    }

    @Override
    public int getDescription() {
        return 0;
    }

    public VideoBasic parse(String str, String regexStr){

    }
}
