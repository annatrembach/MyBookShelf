package MyBookShelf.service;

import MyBookShelf.models.Image;
import MyBookShelf.models.Role;
import MyBookShelf.models.User;
import MyBookShelf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    public boolean addUser(User user, MultipartFile file) throws IOException {
        //User image
        Image userPicture;
        if (file.getSize() != 0) {
            userPicture = toImageEntity(file);
            user.setUserPicture(userPicture);
        }
        //Other user details
        String userEmail = user.getEmail();
        if (userRepository.findByEmail(userEmail) != null) return false;
        user.setActive(true);
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setImageName(file.getName());
        image.setImageOriginalFileName(file.getOriginalFilename());
        image.setImageContentType(file.getContentType());
        image.setImageSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }
}
