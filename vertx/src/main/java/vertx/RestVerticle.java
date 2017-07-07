package vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import vertx.domain.Blog;

import static java.lang.System.out;

public class RestVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {

        final Router router = Router.router(vertx);

        router.route("/test/").handler(ctx -> {
            ctx.response()
                    .setStatusCode(200)
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .end(Json.encode(new Blog("title", "body")));
        });

        EventBus eventBus = vertx.eventBus();

//        eventBus.<String>consumer("addr", event -> {
//           out.println(Json.decodeValue(event.body(), Blog.class).getTitle());
//        });
//        eventBus.publish("addr", Json.encode(new Blog("a", "b")));

        eventBus.registerDefaultCodec(Blog.class, new MessageCodec<Blog, Blog>() {
            @Override
            public void encodeToWire(Buffer buffer, Blog blog) {
                String str = Json.encode(blog);
                buffer.appendInt(str.length());
                buffer.appendString(str);
            }

            @Override
            public Blog decodeFromWire(int pos, Buffer buffer) {
                int length = buffer.getInt(pos);
                String jsonStr = buffer.getString(pos + 4, pos + length);
                return Json.decodeValue(jsonStr, Blog.class);
            }

            @Override
            public Blog transform(Blog blog) {
                return blog;
            }

            @Override
            public String name() {
                return "BlogCodec";
            }

            @Override
            public byte systemCodecID() {
                return -1;
            }
        });

        eventBus.<Blog>consumer("addr", event -> {
           out.println(event.body().getTitle());
        });
        eventBus.<Blog>publish("addr", new Blog("a", "b"));

        vertx
                .createHttpServer()
                .requestHandler(router::accept)
                .listen(8080);

        out.println("Deployed VertxRestApp on http://localhost:8080");
    }
}
