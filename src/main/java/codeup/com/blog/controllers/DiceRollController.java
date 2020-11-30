package codeup.com.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class DiceRollController {
    @GetMapping("/roll-dice")
    public String displayRollDice(){
        return "/roll-dice";
    }

    @GetMapping("/roll-dice/{chosen}")
    public String chosenNumber(@PathVariable int chosen, Model model){
        int randomNumber = ThreadLocalRandom.current().nextInt(1, 6 + 1);

        model.addAttribute("randomNumber", randomNumber);
        model.addAttribute("chosen", chosen);
        model.addAttribute("isMatching", chosen == randomNumber);

        return "/roll-dice";
    }
}
