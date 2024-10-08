package MyBookShelf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String homepage(Model model) {
        model.addAttribute("MainTitle", "MyBookShelf");
        return "MainPage";
    }

}
