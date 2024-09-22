package MyBookShelf.controllers;

import MyBookShelf.models.Image;
import MyBookShelf.models.ResponseData;
import MyBookShelf.models.User;
import MyBookShelf.repository.ImageRepository;
import MyBookShelf.repository.UserRepository;
import MyBookShelf.service.ImageService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class ImageController {

    public ImageService imageService;
    public ImageRepository imageRepository;
    public UserRepository userRepository;

    public ImageController(ImageService imageService, ImageRepository imageRepository, UserRepository userRepository) {
        this.imageService = imageService;
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/upload")
    public ResponseData uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        Image image = imageService.saveImage(file);
        String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(image.getImageId())
                .toUriString();

        return new ResponseData(image.getImageName(),
                downloadUrl,
                file.getContentType(),
                file.getSize());
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {
        Image image = imageService.getImage(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getImageType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getImageName() + "\"")
                .body(new ByteArrayResource(image.getImageData()));
    }

    @GetMapping("images/{userId}")
    public ResponseEntity<Resource> getUserPicture(@PathVariable Long userId) throws Exception {
        User user = userRepository.findByUserId(userId);
        Image image = user.getUserPicture();
        if (image != null && image.getImageData() != null) {
            ByteArrayResource resource = new ByteArrayResource(image.getImageData());
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(image.getImageType()))
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
