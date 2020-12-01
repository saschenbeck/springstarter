package codeup.com.blog.controllers;

import codeup.com.blog.models.Ad;
import codeup.com.blog.models.Post;
import codeup.com.blog.repos.AdRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdController {

    private final AdRepository adDao;

    public AdController(AdRepository adDao){
        this.adDao = adDao;
    }

    @GetMapping("/ads")
    public String index(Model model){
        model.addAttribute("ads", adDao.findAll());
        return "ads/index";
    }

    @GetMapping("/ads/{id}")
    public String show(@PathVariable long id, Model model){
        Post post = new Post("Post " + id, "Some stuff about " + id);
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/ads/create")
    public String showCreateForm(){
        return "ads/new";
    }

    @PostMapping("/ads/create")
    public String createAd(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "description") String desc
    ){
        Ad ad = new Ad(title, desc);
        Ad dbAd = adDao.save(ad);
        return "ads/new";
    }
}
