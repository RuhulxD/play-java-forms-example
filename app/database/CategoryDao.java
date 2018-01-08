package database;

import models.Category;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.Collection;
import java.util.Collections;
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

    public List<Category> getCategories(String name) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select f from Category f where f.name=?1", Category.class);
            q.setParameter(1, name);
            return q.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }

        return null;
    }

    public List<Category> selectAllCategories() {
        EntityManager em = getEntityManager();
        try {
            List<Category> result = em.createQuery("SELECT e FROM Category e").getResultList();
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return Collections.emptyList();
    }

    public List<Category> getCategories(String name, int type, int page, int limit) {
        limit = ((limit <= 0) || (limit > MAX_RESULT_LIMIT)) ? DEFAULT_RESULT_LIMIT : limit;
        EntityManager em = getEntityManager();
        try {
            String str = "select e from Category e ";
            str += " where e.name = ?1 ";
            if (type > 0) {
                str += " and e.type = ?2 ";
            }
            Query q = em.createQuery(str);
            q.setParameter(1, name);
            if (type > 0) {
                q.setParameter(2, type);
            }
            q.setFirstResult(page);
            q.setMaxResults(limit);
            List<Category> result = q.getResultList();
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return Collections.emptyList();
    }


    public boolean addOneCategory(Category basic) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(basic);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return false;
    }

    public boolean deleteCategory(String id) {
        EntityManager em = getEntityManager();
        try {

            em.getTransaction().begin();
            Query q = em.createQuery("delete from Category f where f.name=?1 ");
            q.setParameter(1, id);
            int changed = q.executeUpdate();
            em.getTransaction().commit();
            return changed > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return false;
    }

    public boolean deleteCategory(String id, String value) {
        EntityManager em = getEntityManager();
        try {

            em.getTransaction().begin();
            Query q = em.createQuery("delete from Category f where f.name=?1 and f.value=?2 ");
            q.setParameter(1, id);
            q.setParameter(2, value);
            int changed = q.executeUpdate();
            em.getTransaction().commit();
            return changed > 0;
        } catch (Exception ex) {

        } finally {
            em.close();
        }
        return false;
    }

    public EntityManager getEntityManager() {
        return jpaApi.em("default");
    }
}
