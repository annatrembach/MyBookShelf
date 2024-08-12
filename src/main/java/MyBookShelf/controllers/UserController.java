package MyBookShelf.controllers;

import MyBookShelf.models.User;
import MyBookShelf.repository.UserRepository;
import MyBookShelf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping("/HomePage")
    public String login(Model model) {
        model.addAttribute("HomeTitle", "MyBookShelf");
        return "HomePage";
    }

    @GetMapping("/Registration")
    public String registration(Model model) {
        return "Registration";
    }

    @PostMapping("/Registration")
    public String registrationPost(User user){
        userService.addUser(user);
        return "redirect:/login";
    }
}
