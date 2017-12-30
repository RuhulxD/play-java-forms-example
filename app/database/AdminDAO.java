package database;

import models.Application;
import models.Category;
import models.PlayList;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

public class AdminDAO {

    private final JPAApi jpaApi;

    @Inject
    public AdminDAO(JPAApi jpaApi) {
        this.jpaApi = jpaApi;
    }

    public boolean addApplication(Application app) {
        return true;
    }

    public boolean addPlayList(PlayList playList) {
        return true;
    }

    public boolean addCategory(Category category) {
        return true;

    }

    public List<Application> getApplications() {
        return Collections.emptyList();


    }

    public List<Category> getCategories(String id, int start, int limit) {
        EntityManager em = getEm();
        Query q = em.createNativeQuery("select * from category where name=?1");
        q.setParameter(1, id);
        q.setMaxResults(limit);
        q.setFirstResult(start);
        q.getResultList();
        return q.getResultList();
    }



    public List<PlayList> getPlayLists() {
        return Collections.emptyList();

    }

    public EntityManager getEm() {
        return jpaApi.em("default");
    }
}
