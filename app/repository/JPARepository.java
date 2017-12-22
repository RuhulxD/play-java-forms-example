package repository;

import play.db.jpa.JPAApi;

import javax.inject.*;
import javax.persistence.*;
import java.util.concurrent.*;

@Singleton
public class JPARepository {
    private JPAApi jpaApi;
    private DatabaseExecutionContext executionContext;

    @Inject
    public JPARepository(JPAApi api, DatabaseExecutionContext executionContext) {
        this.jpaApi = api;
        this.executionContext = executionContext;
    }
    public CompletionStage<Long> runningWithTransaction() {
        return CompletableFuture.supplyAsync(() -> {
            // lambda is an instance of Function<EntityManager, Long>
            return jpaApi.withTransaction(entityManager -> {
                Query query = entityManager.createNativeQuery("select max(age) from people");
                return (Long) query.getSingleResult();
            });
        }, executionContext);
    }
    public CompletionStage<Void> runningWithRunnable() {
        // lambda is an instance of Runnable
        return CompletableFuture.runAsync(() -> {
            jpaApi.withTransaction(() -> {
                EntityManager em = jpaApi.em();
                Query query = em.createNativeQuery("update people set active = 1 where age > 18");
                query.executeUpdate();
            });
        }, executionContext);
    }
}