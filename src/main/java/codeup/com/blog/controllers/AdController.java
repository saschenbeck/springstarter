package codeup.com.blog.controllers;

import codeup.com.blog.models.Ad;
import codeup.com.blog.models.Post;
import codeup.com.blog.models.User;
import codeup.com.blog.repos.AdRepository;
import codeup.com.blog.repos.UserRepository;
import codeup.com.blog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdController {

    private final AdRepository adDao;
    private final UserRepository userDao;
    private final EmailService emailService;

    public AdController(AdRepository adDao, UserRepository userDao, EmailService emailService){
        this.adDao = adDao;
        this.userDao = userDao;
        this.emailService = emailService;
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
    public String showCreateForm(Model model){
        model.addAttribute("ad", new Ad());
        return "ads/new";
    }

    @PostMapping("/ads/create")
    public String createAd(@ModelAttribute Ad adToBeSaved){
        User user = userDao.getOne(1L);
        adToBeSaved.setOwner(user);
        Ad dbAd = adDao.save(adToBeSaved);
        emailService.prepareAndSend(dbAd, "Ad has been created", "You can find it with the id of " + dbAd.getId());
        return "redirect:/ads/" + dbAd.getId();
    }

    @GetMapping("/ads/{id}/edit")
    public String showEditForm(@PathVariable long id, Model viewModel){
        viewModel.addAttribute("ad", adDao.getOne(id));
        return "ads/edit";
    }

    @PostMapping("/ads/{id}/edit")
    public String editAd(@ModelAttribute Ad adToBeUpdated){
        User user = userDao.getOne(1L);
        adToBeUpdated.setOwner(user);
        adDao.save(adToBeUpdated);
        return "redirect:/ads/";
    }

    @PostMapping("/ads/{id}/delete")
    public String deleteAd(@PathVariable long id){
        adDao.deleteById(id);
        return "redirect:/ads";
    }
}
