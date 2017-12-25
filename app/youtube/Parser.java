package youtube;

public interface Parser {
    public int getEpisode(String str) throws Exception;

    public String getName(String str) throws Exception;

    public int getSeason(String str) throws Exception;

    public String getActors(String str) throws Exception;
    public String getTitle(String str) throws Exception;

}
