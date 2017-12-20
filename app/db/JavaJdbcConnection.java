/*
 * Copyright (C) 2009-2017 Lightbend Inc. <https://www.lightbend.com>
 */
package db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import javax.inject.Inject;

import play.mvc.Controller;
import play.db.NamedDatabase;
import play.db.Database;

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
            try {
                connection.prepareStatement("insert into test (id, count, name) values (1,2,\'asdf\')").executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return;
        }, executionContext);
    }

}