package springboot_rest_db.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import springboot_rest_db.domain.Blog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot_rest_db.repository.BlogRepository;

/**
 * @author <a href="mailto:rnovikov@wiley.com">Roman Novikov</a>
 */
@RestController(value = "/")
public class HelloController {

    @Autowired
    BlogRepository blogRepository;

    @GetMapping("test")
    public Blog test() {
        return blogRepository.findOne(1L);
    }

}
