package vertx_thymeleaf;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.TemplateHandler;
import io.vertx.ext.web.templ.TemplateEngine;
import io.vertx.ext.web.templ.ThymeleafTemplateEngine;
import vertx_thymeleaf.domain.Blog;

import static java.lang.System.out;

public class RestVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {

        final Router router = Router.router(vertx);

        TemplateEngine engine = ThymeleafTemplateEngine.create();
        TemplateHandler handler = TemplateHandler.create(engine);

        router.route("/test/").handler(ctx -> {
            ctx.response()
                    .setStatusCode(200)
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .end(Json.encode(new Blog("title", "body")));
        });

        vertx
                .createHttpServer()
                .requestHandler(router::accept)
                .listen(8080);

        out.println("Deployed VertxRestApp on http://localhost:8080");
    }
}
