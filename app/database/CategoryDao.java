package database;

import models.Category;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

@Singleton
public class CategoryDao {
    private final JPAApi jpaApi;
    private final int MAX_RESULT_LIMIT = 50;
    private final int DEFAULT_RESULT_LIMIT = 20;

    @Inject
    public CategoryDao(JPAApi jpaApi) {
        this.jpaApi = jpaApi;
    }

    public Category selectCategory(String name) {
        EntityManager em = jpaApi.em("default");
        Query q = em.createQuery("select f from Category f where f.name=?1", Category.class);
        q.setParameter(1, name);

        // its really "awesome" for JPA hibernate that if i dont have result
        // it punch to my face one big exception"
        Category result = null;
        try {
            result = (Category) q.getSingleResult();
        } catch (NoResultException ex) {
            // nothing needed here
        }
        em.close();
        return result;
    }

    public List<Category> selectAllCategories() {
        EntityManager em = jpaApi.em("default");
        List<Category> result = em.createQuery("SELECT e FROM Category e").getResultList();
        em.close();
        return result;
    }

    public List<Category> getCategories(int page, String name, int limit) {
        limit = ((limit <= 0) || (limit > MAX_RESULT_LIMIT)) ? DEFAULT_RESULT_LIMIT : limit;
        EntityManager em = getEntityManager();
        String str = "select e from Category e ";
        str += " where e.name = ?1 ";
        Query q = em.createQuery(str);
        q.setParameter(1, name);
        q.setFirstResult(page);
        q.setMaxResults(limit);
        List<Category> result = q.getResultList();
        return result;
    }


    public boolean addOneCategory(Category basic) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(basic);
        em.getTransaction().commit();
        em.close();
        return true; //TODO not so good
    }

    public boolean deleteCategory(String id) {
        EntityManager em = jpaApi.em("default");
        em.getTransaction().begin();
        Query q = em.createQuery("delete from Category f where f.name=?1 ");
        q.setParameter(1, id);
        int changed = q.executeUpdate();
        em.getTransaction().commit();
        em.close();
        return changed > 0;
    }

    public boolean deleteCategory(String id, String value) {
        EntityManager em = jpaApi.em("default");
        em.getTransaction().begin();
        Query q = em.createQuery("delete from Category f where f.name=?1 and f.value=?2 ");
        q.setParameter(1, id);
        q.setParameter(2, value);
        int changed = q.executeUpdate();
        em.getTransaction().commit();
        em.close();
        return changed > 0;
    }

    public EntityManager getEntityManager() {
        return jpaApi.em("default");
    }


    public List<Category> search(String filter, int page, int limit) {
        limit = (limit < 0 || limit > MAX_RESULT_LIMIT) ? DEFAULT_RESULT_LIMIT : limit;

        EntityManager em = getEntityManager();
        String str = "select v from Category v where ";
        if (filter == null) {
            filter = "";
        }
        str += " v.name = ?1 ";
        Query query = em.createQuery(str);
        query.setParameter(1, filter);
        query.setFirstResult(page);
        query.setMaxResults(limit);
        List<Category> result = query.getResultList();
        em.close();
        return result;
    }

}
