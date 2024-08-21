package MyBookShelf.controllers;

import MyBookShelf.models.User;
import MyBookShelf.repository.UserRepository;
import MyBookShelf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class UserController {

    @Autowired
    public UserService userService;

    @Autowired
    public UserRepository userRepository;

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
    public String registrationPost(Model model, User user, @RequestParam("userPicture") MultipartFile file) throws IOException {
        userService.addUser(user, file);
        return "redirect:/login";
    }


    @GetMapping("/HomePage/MyProfile/{userId}")
    public String userInfo(@PathVariable Long userId, Model model) {
        User user = userRepository.findByUserId(userId);
        model.addAttribute("user", user);
        model.addAttribute("image", user.getUserPicture());
        return "User";
    }
}
