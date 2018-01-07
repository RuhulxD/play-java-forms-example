package database;

import models.PlayList;
import models.VideoBasic;
import play.db.jpa.JPAApi;
import scala.collection.mutable.ReusableBuilder;
import utility.Utils;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Arrays;
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

    public PlayList getPlayListDetails(String id) {

        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select a from playlist a", PlayList.class);
            return (PlayList) q.getSingleResult();
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public PlayList getPlayList(String id, int start, int limit) {
        EntityManager em = getEntityManager();
        try {
            PlayList playList = getPlayListDetails(id);
            if (playList == null) {
                return null;
            }
            Query query = em.createNativeQuery("select * from videos as b left join PlayList_VideoBasic as pb on pb.videos_yURL=b.yurl" +
                    " where pb.PlayList_id=?1 order by b.episode asc limit ?2 , ?3", VideoBasic.class);
            System.err.println("###############################################################");
            query.setParameter(1, id);
            query.setParameter(2, start);
            query.setParameter(3, limit);
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
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            for (PlayList item : playLists) {
                em.merge(item);
            }
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return true;
    }

    public EntityManager getEntityManager() {
        return jpaApi.em("default");
    }
}
