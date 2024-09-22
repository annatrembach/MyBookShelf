package MyBookShelf.controllers;

import MyBookShelf.models.Image;
import MyBookShelf.models.User;
import MyBookShelf.repository.UserRepository;
import MyBookShelf.service.ImageService;
import MyBookShelf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UserController {

    @Autowired
    public UserService userService;

    @Autowired
    public ImageService imageService;

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
            Image image = imageService.uploadImageToFileSystem(file);
            user.setUserPicture(image);
        }
        userService.addUser(user);
        return "redirect:/Login";
    }

    @GetMapping("/HomePage/MyProfile")
    public String userInfo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        User user = userRepository.findByEmail(email);
        model.addAttribute("user", user);
        return "UserProfile";
    }
}
