/*
 * Copyright (C) 2009-2017 Lightbend Inc. <https://www.lightbend.com>
 */
package repository;

import play.db.Database;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@javax.inject.Singleton
class JavaJdbcConnection {
    private Database db;
    private DatabaseExecutionContext executionContext;

    @Inject
    public JavaJdbcConnection(Database db, DatabaseExecutionContext executionContext) {
        this.db = db;
        this.executionContext = executionContext;
    }

    public CompletionStage<Void> updateSomething() {
        return CompletableFuture.runAsync(() -> {
            // get jdbc connection
            Connection connection = db.getConnection();

            // do whatever you need with the db connection
            try {

                boolean ex = connection.createStatement().execute("select * from video_full");
            } catch (SQLException e) {
                e.printStackTrace();
            }


            return;
        }, executionContext);
    }



}