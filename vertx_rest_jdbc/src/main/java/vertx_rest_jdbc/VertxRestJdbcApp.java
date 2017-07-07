package vertx_rest_jdbc;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

import static java.lang.System.out;

public class VertxRestJdbcApp {

    public static void main(String[] args) {
        VertxOptions options = new VertxOptions();
        Vertx vertx = Vertx.vertx(options);
        vertx.deployVerticle(RestVerticle.class.getName(), new DeploymentOptions().setInstances(8), event -> {
            if (event.succeeded()) {
                out.println("Started Application!");
            } else {
                event.cause().printStackTrace();
            }
        });
    }
}
