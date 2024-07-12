package MyBookShelf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String homepage(Model model) {
        model.addAttribute("HomeTitle", "MyBookShelf");
        return "HomePage";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("LoginTitle", "Login");
        return "Login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("RegistrationTitle", "Registration");
        return "Registration";
    }
}
