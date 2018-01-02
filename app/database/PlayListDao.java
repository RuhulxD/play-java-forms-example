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

    public PlayList getPlayList(String id, int start, int limit) {

        EntityManager em = getEntityManager();
        PlayList playList = em.find(PlayList.class, id);

//        Query query =
//        query.setFirstResult(start);
//        query.setMaxResults(limit);
//        query.setParameter(1, id);
//        PlayList playList = null;
//        try {
//            playList = (PlayList) query.getSingleResult();
//        } catch (Exception ex) {
//
//        }
        em.close();
        return playList;

    }

    public List<String> getPlayLista(){
        EntityManager em = getEntityManager();
        Query query = em.createNativeQuery("select * from VideoBasic as b left join PlayList_VideoBasic as pb on pb.videos_yURL=b.yurl" +
                " where pb.PlayList_id='PLDQTrGfhmhLiTobhWiRzwh7fr_4jt-iXg' order by b.episode asc limit 10,10", VideoBasic.class);

        System.err.println("###############################################################");
        List list = query.getResultList();
//        for(Object obj: list){
//            List<String> a= Arrays.asList(obj);
//            for(Object item: a){
//                System.out.println("item:"+item);
//            }
//        }
        Utils.print(list);
        return  list;

    }

    //update/delete
    public boolean deletePlayList(String id) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("delete from PlayList e where e.id=?1 ");
        query.setParameter(1, id);
        int i = query.executeUpdate();
        return i > 0;
    }

    public boolean deleteFromPlayList(String id, String yid) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("delete from PlayList e where e.id=?1 and yid=?2");
        query.setParameter(1, id);
        query.setParameter(2, yid);
        int i = query.executeUpdate();
        return i > 0;
    }

    // add /create
    public boolean addToPlayList(PlayList playList) {
        return addToPlayList(Arrays.asList(playList));
    }

    public boolean addToPlayList(List<PlayList> playLists) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        ;
        for (PlayList item : playLists) {
//            EntityManager em1 = getEntityManager();
//            em1.getTransaction().begin();
//
//            for(VideoBasic basic: item.getVideos()){
//                em1.persist(basic);
//            }
//            em1.getTransaction().commit();
//            em1.close();
            em.persist(item);
        }
        em.getTransaction().commit();
        em.close();
        return true;
    }

    public EntityManager getEntityManager() {
        return jpaApi.em("default");
    }

}
