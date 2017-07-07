package vertx;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

import static java.lang.System.out;

public class VertxApp {

    public static void main(String[] args) {
        VertxOptions options = new VertxOptions();
        Vertx vertx = Vertx.vertx(options);
        vertx.deployVerticle(RestVerticle.class.getName(), new DeploymentOptions().setInstances(1), event -> {
            out.println("Started Application!");
        });
    }
}
