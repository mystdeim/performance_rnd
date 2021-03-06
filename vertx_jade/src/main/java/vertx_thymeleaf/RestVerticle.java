package vertx_thymeleaf;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.templ.TemplateEngine;
import io.vertx.ext.web.templ.JadeTemplateEngine;

import static java.lang.System.out;

public class RestVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {

        final Router router = Router.router(vertx);

        TemplateEngine engine = JadeTemplateEngine.create();

        router.route("/test/").handler(ctx -> {
            ctx.put("title", "Home page!");
            engine.render (ctx, "templates/", "home.jade", res -> {
                if (res.succeeded()) {
                    ctx.response().end(res.result());
                } else {
                    ctx.fail(res.cause());
                }
            });
        });

        vertx
                .createHttpServer()
                .requestHandler(router::accept)
                .listen(8080);

        out.println("Deployed VertxJadeApp on http://localhost:8080");
    }
}
