package database;

import models.VideoBasic;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Singleton
public class BackendDao {
    private final JPAApi jpaApi;
    EntityManager em;

    @Inject
    public BackendDao(JPAApi jpaApi){
        this.jpaApi = jpaApi;
    }

    public VideoBasic selectOneVideo(Long id) {
        EntityManager em = jpaApi.em("default");
        Query q = em.createQuery("select f from video_full f where f.id=?1", VideoBasic.class);
        q.setParameter(1, id);

        // its really "awesome" for JPA hibernate that if i dont have result
        // it punch to my face one big exception"
        VideoBasic result = null;
        try {
            result = (VideoBasic) q.getSingleResult();
        } catch (NoResultException ex) {
            // nothing needed here
        }
        em.close();
        return result;
    }

    public List<VideoBasic> selectAllVideoBasics() {
        EntityManager em = jpaApi.em("default");
        List<VideoBasic> result = em.createQuery("SELECT e FROM VideoBasic e").getResultList();
        em.close();
        return result;
    }


    public boolean addOneVideoBasic(VideoBasic basic) {
        EntityManager em = jpaApi.em("default");
        em.getTransaction().begin();
        em.persist(basic);
        em.getTransaction().commit();
        em.close();
        return true; //TODO not so good
    }

    public boolean deleteOneVideoBasic(long id) {
        EntityManager em = jpaApi.em("default");
        em.getTransaction().begin();
        Query q = em.createQuery("delete from VideoBasic f where f.id=?1");
        q.setParameter(1, id);
        int changed = q.executeUpdate();
        em.getTransaction().commit();
        em.close();
        return changed > 0;
    }
    public EntityManager getEntityManager(){
        return jpaApi.em("default");
    }

    public List<VideoBasic> search(Map<String, String> map){

        String query = "";
        boolean flag = true;
        for(String key: map.keySet()){
            if(flag){
                flag = false;
                query +=" and ";
            }
            query += key +" like %"+map.get(key)+"% ";
        }
        return search(query);
    }
    public List<VideoBasic> search(String query){
        em = getEntityManager();
        List<VideoBasic> result =em.createQuery("select v from VideoBasic v where " + query ).getResultList();
        em.close();
        return result;
    }


}
