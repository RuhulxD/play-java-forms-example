package database;

import javafx.scene.shape.TriangleMesh;
import models.Application;
import models.Category;
import models.PlayList;
import models.VideoBasic;
import play.data.Form;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import scala.App;
import views.html.defaultpages.badRequest;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

public class AdminDAO {

    private final JPAApi jpaApi;

    @Inject
    public AdminDAO(JPAApi jpaApi) {
        this.jpaApi = jpaApi;
    }

    public boolean addApplication(Application app) throws Exception {
        System.out.println("#########Creating apps..");
        try {
            EntityManager em = getEm();
            em.getTransaction().begin();
            em.persist(app);
            em.getTransaction().commit();
            em.close();
        }catch (Exception ex){
            ex.printStackTrace();
            throw ex;
        }
        return true;
    }

    public boolean addPlayList(PlayList playList) {
        return true;
    }

    public boolean addCategory(Category category) {
        System.out.println("#########Creating apps..");
        try {
            EntityManager em = getEm();
            em.getTransaction().begin();
            em.persist(category);
            em.getTransaction().commit();
            em.close();
        }catch (Exception ex){
            ex.printStackTrace();
            throw ex;
        }
        return true;
    }

    public List<Application> getApplications() {
        EntityManager em = getEm();
        List<Application> apps = em.createQuery("select a from Application a", Application.class).getResultList();
        em.close();
        return apps;
    }
    public boolean deleteApplication( String id) {
        EntityManager em = getEm();
        em.getTransaction().begin();
        Query query = em.createQuery("delete from Application where name = ?1 ");
        query.setParameter(1, id);
        int i = query.executeUpdate();
        em.getTransaction().commit();
        em.close();
        return i>0;
    }

    public List<Category> getCategories(String id, int start, int limit) {
        EntityManager em = getEm();
        Query q = em.createQuery("select c from Category c where name=?1", Category.class);
        q.setParameter(1, id);
        q.setMaxResults(limit);
        q.setFirstResult(start);
        List<Category> resultList = q.getResultList();

        em.close();
        return resultList;
    }

    public List<Category> getCategories(String id) {
        return getCategories(id, 0, 100);
    }
    public boolean deleteCategory(String id, String value) {
        EntityManager em = getEm();
        em.getTransaction().begin();
        Query q = em.createQuery("delete from Category where name=?1 and value=?2 ");
        q.setParameter(1, id);
        q.setParameter(2, value);
        int x = q.executeUpdate();
        em.getTransaction().commit();
        em.close();
        return x>0;
    }


    public List<PlayList> getPlayLists() {
        return Collections.emptyList();
    }

    public EntityManager getEm() {
        return jpaApi.em("default");
    }
}
