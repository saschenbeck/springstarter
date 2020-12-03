package codeup.com.blog.controllers;

import codeup.com.blog.models.Ad;
import codeup.com.blog.models.Post;
import codeup.com.blog.models.User;
import codeup.com.blog.repos.AdRepository;
import codeup.com.blog.repos.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdController {

    private final AdRepository adDao;
    private final UserRepository userDao;

    public AdController(AdRepository adDao, UserRepository userDao){
        this.adDao = adDao;
        this.userDao = userDao;
    }

    @GetMapping("/ads")
    public String index(Model model){
        model.addAttribute("ads", adDao.findAll());
        return "ads/index";
    }

    @GetMapping("/ads/{id}")
    public String show(@PathVariable long id, Model model){
        model.addAttribute("ad", adDao.getOne(id));
        return "ads/show";
    }

    @GetMapping("/ads/create")
    public String showCreateForm(){
        return "ads/new";
    }

    @PostMapping("/ads/create")
    public String createAd(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "description") String desc){
        User user = userDao.getOne(1L);
        Ad ad = new Ad(title, desc, user);
        Ad dbAd = adDao.save(ad);
        return "redirect:/ads/" + dbAd.getId();
    }

    @GetMapping("/ads/{id}/edit")
    public String showEditForm(@PathVariable long id, Model viewModel){
        viewModel.addAttribute("ad", adDao.getOne(id));
        return "ads/edit";
    }

    @PostMapping("/ads/{id}/edit")
    public String editAd(
            @PathVariable long id,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "description") String desc
    ){
        Ad dbAd = adDao.getOne(id);
        dbAd.setTitle(title);
        dbAd.setDescription(desc);
        adDao.save(dbAd);
        return "redirect:/ads/" + dbAd.getId();
    }

    @PostMapping("/ads/{id}/delete")
    public String deleteAd(@PathVariable long id){
        adDao.deleteById(id);
        return "redirect:/ads";
    }
}
