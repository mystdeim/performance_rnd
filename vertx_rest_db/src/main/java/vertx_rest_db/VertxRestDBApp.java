package vertx_rest_db;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

import static java.lang.System.out;

public class VertxRestDBApp {

    public static void main(String[] args) {
        VertxOptions options = new VertxOptions();
        Vertx vertx = Vertx.vertx(options);
        vertx.deployVerticle(RestVerticle.class.getName(), new DeploymentOptions().setInstances(8), event -> {
            out.println("Started Application!");
        });
    }
}
