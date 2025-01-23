package MyBookShelf.service;

import MyBookShelf.models.Image;
import MyBookShelf.models.User;
import MyBookShelf.repository.ImageRepository;
import MyBookShelf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    public ImageRepository imageRepository;

    @Autowired
    public UserRepository userRepository;

    private final String FOLDER_PATH="A://MyBookShelf//imageData";

    public Image uploadImageToFileSystem(MultipartFile file) throws IOException {
        String filePath = FOLDER_PATH + file.getOriginalFilename();

        Image image = imageRepository.save(Image.builder()
                .imageName(file.getOriginalFilename())
                .imageType(file.getContentType())
                .imageFilePath(filePath).build());

        file.transferTo(new File(filePath));

        return image;
    }

    public byte[] downloadImageFromFileSystem(Long userId) throws IOException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent() && user.get().getUserPicture() != null) {
            String imageFilePath = user.get().getUserPicture().getImageFilePath();
            return Files.readAllBytes(new File(imageFilePath).toPath());
        } else {
            throw new FileNotFoundException("Image not found for user with ID: " + userId);
        }
    }
}

