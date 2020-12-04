package codeup.com.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @GetMapping("/")
    public String showLandingPage(){
        return "ads/index";
    }

    @GetMapping("/home")
    public String welcome(){
        return "/home"; //This return looks to a file in templates folder
    }

    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable String name, Model model){
        model.addAttribute("name", name);
        return "/hello";
    }

}
