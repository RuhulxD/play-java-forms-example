package database;

import models.PlayList;
import models.VideoBasic;
import play.db.jpa.JPAApi;
import utility.Utils;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Singleton
public class PlayListDao {
    private final JPAApi jpaApi;
    private final int MAX_RESULT_LIMIT = 50;
    private final int DEFAULT_RESULT_LIMIT = 20;

    @Inject
    public PlayListDao(JPAApi jpaApi) {
        this.jpaApi = jpaApi;
    }
    //getPlaylist

    public List<PlayList> getPlayLists(int start, int limit) {

        EntityManager em = getEntityManager();
        try {
            Query q = em.createNativeQuery("select a.title, a.total, a.thumb, a.id, a.thumb1 from playlist a", PlayList.class);
            q.setFirstResult(start);
            q.setMaxResults(limit);
//            q.setParameter(1, start);
//            q.setParameter(2, limit);

            return q.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }
    public PlayList getPlayListDetails(String id) {

        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select a from PlayList a where a.id = ?1", PlayList.class);
            q.setParameter(1, id);
            return (PlayList) q.getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public PlayList getPlayList(String id, int start, int limit) {
        EntityManager em = getEntityManager();
        try {
            System.err.println("playlist id==" + id);
            PlayList playList = getPlayListDetails(id);
            if (playList == null) {
                return null;
            }
            Utils.print(playList);
            Query query = em.createNativeQuery("select * from videos as b left join playlist_videos as pb on pb.videos_yURL=b.yurl" +
                    " where pb.PlayList_id=?1 order by b.episode asc", VideoBasic.class);
            System.err.println("###############################################################");
            query.setParameter(1, id);
            query.setFirstResult(start);
            query.setMaxResults(limit);
            List<VideoBasic> resultList = query.getResultList();
            playList.setVideos(resultList);
            return playList;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    //update/delete
    public boolean deletePlayList(String id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("delete from PlayList e where e.id=?1 ");
            query.setParameter(1, id);
            int i = query.executeUpdate();
            return i > 0;

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return false;
    }

    public boolean deleteFromPlayList(String id, String yid) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("delete from PlayList_Video e where e.id=?1 and yid=?2");
            query.setParameter(1, id);
            query.setParameter(2, yid);
            int i = query.executeUpdate();
            return i > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return false;
    }

    // add /create
    public boolean addToPlayList(PlayList playList) {
        return addToPlayList(Arrays.asList(playList));
    }

    public boolean addToPlayList(List<PlayList> playLists) {
//        EntityManager em = getEntityManager();
        try {
//            em.getTransaction().begin();
            for (PlayList item : playLists) {
                //em.merge(item);
                insertPlaylist(item);
            }
//            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
//            em.close();
        }
        return true;
    }

    public EntityManager getEntityManager() {
        return jpaApi.em("default");
    }


    private String insertIntoPlayVideo() {
        return "insert IGNORE into playlist_videos  (PlayList_id, videos_yURL)   values   (?1, ?2)";
    }

    private Query bindInsertIntoPlayVideo(EntityManager em, String playListId, String videoId) {
        Query query = em.createNativeQuery(insertIntoPlayVideo());
        query.setParameter(1, playListId);
        query.setParameter(2, videoId);
        return query;
    }

    private String insertIntoVideo() {
        return "insert IGNORE into videos " +
                " (actors, channelId, description, episode, genre, imdbID," +
                "  name, poster, publishedTime, region, season, tags," +
                "  title, type, year, yURL) " +
                "    values" +
                "        (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12, ?13, ?14, ?15, ?16) " +
                " on duplicate key update " +
                "  actors=?1, channelId=?2, description=?3, episode=?4, genre=?5, imdbID=?6," +
                "  name=?7, poster=?8, publishedTime=?9, region=?10, season=?11, tags=?12," +
                "  title=?13, type=?14, year=?15 " +
                "";
    }

    private Query bindVideo(EntityManager em, VideoBasic video) {
        Query q = em.createNativeQuery(insertIntoVideo());
        q.setParameter(1, video.actors);
        q.setParameter(2, video.channelId);
        q.setParameter(3, video.description);
        q.setParameter(4, video.episode);
        q.setParameter(5, video.genre);
        q.setParameter(6, video.imdbID);
        q.setParameter(7, video.name);
        q.setParameter(8, video.poster);
        q.setParameter(9, video.publishedTime);
        q.setParameter(10, video.region);
        q.setParameter(11, video.season);
        q.setParameter(12, video.tags);
        q.setParameter(13, video.title);
        q.setParameter(14, video.type);
        q.setParameter(15, video.year);
        q.setParameter(16, video.yURL);
        return q;
    }

    private String insertIntoPlayList() {
        return "insert into playlist (thumb, thumb1, title, id, total) values " +
                "(?1, ?2, ?3, ?4, ?5) " +
                " on duplicate key update " +
                " thumb=?1, thumb1=?2, title=?3, total=?5 ";
    }

    private Query bindPlaylist(EntityManager em, PlayList playList) {
        Query q = em.createNativeQuery(insertIntoPlayList());
        q.setParameter(1, playList.thumb);
        q.setParameter(2, playList.thumb1);
        q.setParameter(3, playList.title);
        q.setParameter(4, playList.id);
        q.setParameter(5, playList.total);
        return q;
    }

    private boolean insertPlaylist(PlayList list){
        EntityManager em = getEntityManager();

        try{
            em.getTransaction().begin();
            bindPlaylist(em, list).executeUpdate();
            for(VideoBasic video: list.getVideos()){
                bindVideo(em, video).executeUpdate();
                bindInsertIntoPlayVideo(em, list.id, video.yURL).executeUpdate();
            }
            em.getTransaction().commit();
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            em.close();
        }
        return false;
    }
}
