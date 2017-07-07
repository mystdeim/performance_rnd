package springboot_thymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import springboot_thymeleaf.domain.Blog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller(value = "/")
public class HelloController {

    @GetMapping("test")
    public String test(Model model) {
        model.addAttribute("title", "Home page");
        return "home";
    }

}
