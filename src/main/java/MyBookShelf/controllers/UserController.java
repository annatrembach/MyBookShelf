package MyBookShelf.controllers;

import MyBookShelf.models.Image;
import MyBookShelf.models.User;
import MyBookShelf.repository.UserRepository;
import MyBookShelf.service.ImageService;
import MyBookShelf.service.ImageServiceImpl;
import MyBookShelf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UserController {

    @Autowired
    public UserService userService;

    @Autowired
    public ImageServiceImpl imageServiceImpl;

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
    public String registrationPost(User user, @RequestParam("file") MultipartFile file) throws Exception {
        if (!file.isEmpty()) {
            Image image = imageServiceImpl.saveImage(file);
            user.setUserPicture(image);
        }
        userService.addUser(user);
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
