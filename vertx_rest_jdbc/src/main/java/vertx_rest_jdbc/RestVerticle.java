package vertx_rest_jdbc;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.web.Router;

import static java.lang.System.out;

public class RestVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {

        JsonObject jdbcConf = new JsonObject()
                .put("url", "jdbc:postgresql://localhost:5432/blog")
                .put("user", "postgres")
                .put("password", "password")
                .put("driver_class", "org.postgresql.Driver")
        ;
        SQLClient client = JDBCClient.createShared(vertx, jdbcConf);

        final Router router = Router.router(vertx);

        router.route("/test/").handler(ctx -> {
            client.getConnection(res -> {
                if (res.succeeded()) {
                    try (SQLConnection connection = res.result()) {
                        connection.query("SELECT * FROM blog where id = 1", res2 -> {
                            if (res2.succeeded()) {
                                ResultSet rs = res2.result();
                                ctx.response()
                                        .setStatusCode(200)
                                        .putHeader("content-type", "application/json; charset=utf-8")
                                        .end(Json.encode(rs.getRows().get(0)));
                            } else {
                                res2.cause().printStackTrace();
                            }
                        });
                    }
                } else {
                    res.cause().printStackTrace();
                }
            });
        });

        vertx
                .createHttpServer()
                .requestHandler(router::accept)
                .listen(8080);

        out.println("Deployed VertxRestJdbcApp on http://localhost:8080");
    }
}
