package database;

import play.db.jpa.JPAApi;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

public class BasicDAO<T> extends QueryStatements {
    private final JPAApi jpaApi;
    Class<T> type;

    public BasicDAO(Class<T> type, String TABLE, String idColumn, JPAApi jpaApi) {
        super(TABLE, idColumn);
        this.jpaApi = jpaApi;
        this.type = type;
    }

    public EntityManager getEm() {
        return jpaApi.em("default");
    }

    //insert
    public T add(T t) {
        System.out.println("adding value to tables.");
        EntityManager em = getEm();
        try {
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("persist failed..." + t);
            try {
                em.merge(t);
                em.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("merging failed..." + t);
                em.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return t;
    }

    // get
    public List<T> getAll() {
        EntityManager em = getEm();
        try {
            Query q = em.createQuery(selectALLq(), type);
            return q.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            em.close();
        }
        return Collections.emptyList();
    }

    public List<T> getList(String id, int start, int limit) {
        EntityManager em = getEm();
        try {
            Query q = em.createQuery(selectOne(), type);
            q.setParameter(1, id);
            q.setFirstResult(start);
            q.setMaxResults(limit);
            return q.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            em.close();
        }
        return Collections.emptyList();
    }

    public List<T> getList(List<String> columns, List<String> values, int start, int limit) {
        EntityManager em = getEm();
        try {
            Query q = em.createQuery(selectOneq(columns), type);
            setQueryParams(q, values);
            q.setFirstResult(start);
            q.setMaxResults(limit);
            return q.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return Collections.emptyList();
    }

    public List<T> getList(String query, List<String> values, int start, int limit) {
        System.out.println("#################query ="+query);
        EntityManager em = getEm();
        try {
            Query q = em.createQuery(query, type);
            setQueryParams(q, values);
            q.setFirstResult(start);
            q.setMaxResults(limit);
            return q.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return Collections.emptyList();
    }

    public T getOne(String id) {
        EntityManager em = getEm();
        try {
            Query q = em.createQuery(selectOneq(), type);
            q.setParameter(1, id);
            return (T) q.getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    public List<T> getByCondition(String conditionString, List<String> values) {
        return getByCondition(conditionString, values, 0, 1000);
    }

    public List<T> getByCondition(String conditionString, List<String> values, int start, int limit) {
        EntityManager em = getEm();
        try {
            Query q = em.createQuery(selectALLq() + " " + conditionString, type);
            setQueryParams(q, values);
            q.setMaxResults(limit);
            q.setFirstResult(start);

            return q.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return Collections.emptyList();
    }

    //delete
    public boolean delete(String id) {
        EntityManager em = getEm();
        try {
            em.getTransaction().begin();
            Query query = em.createQuery(deleteQuery());
            query.setParameter(1, id);
            int x = query.executeUpdate();
            em.getTransaction().commit();
            return x > 0;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            ex.printStackTrace();

        } finally {
            em.close();
        }
        return false;
    }

    public boolean delete(List<String> columns, List<String> values) {
        EntityManager em = getEm();
        try {
            em.getTransaction().begin();
            Query query = em.createQuery(deleteQuery(columns));
            setQueryParams(query, values);
            int x = query.executeUpdate();
            em.getTransaction().commit();
            return x > 0;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            ex.printStackTrace();

        } finally {
            em.close();
        }
        return false;
    }

    private void setQueryParams(Query q, List<String> values) {
        for (int i = 0; i < values.size(); i++) {
            q.setParameter(i + 1, values.get(i));
        }
    }

    public boolean delete() {
        EntityManager em = getEm();
        try {
            em.getTransaction().begin();
            Query query = em.createQuery(deleteAllQuery());
            int x = query.executeUpdate();
            em.getTransaction().commit();

            return x > 0;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            ex.printStackTrace();

        } finally {
            em.close();
        }
        return false;
    }

}
