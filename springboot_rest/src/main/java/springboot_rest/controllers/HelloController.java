package springboot_rest.controllers;

import springboot_rest.domain.Blog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:rnovikov@wiley.com">Roman Novikov</a>
 */
@RestController(value = "/")
public class HelloController {

    @GetMapping("test")
    public Blog test() {
        return new Blog("title", "body");
    }

}
