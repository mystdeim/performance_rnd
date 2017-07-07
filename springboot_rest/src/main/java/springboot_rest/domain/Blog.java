package springboot_rest.domain;

/**
 * @author <a href="mailto:rnovikov@wiley.com">Roman Novikov</a>
 */
public class Blog {

    private String title;
    private String body;

    public Blog(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
