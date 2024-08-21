package MyBookShelf.controllers;

import MyBookShelf.models.Book;
import MyBookShelf.models.Image;
import MyBookShelf.repository.BookRepository;
import MyBookShelf.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayInputStream;

@Controller
public class ImageController {

    @Autowired
    public ImageRepository imageRepository;

    @GetMapping("/images/{imageId}")
    public ResponseEntity<?> getImageById(@PathVariable Long ImageId) {
        Image image = imageRepository.findByImageId(ImageId).orElse(null);
        return ResponseEntity.ok()
                .header("fileName", image.getImageOriginalFileName())
                .contentType(MediaType.valueOf(image.getImageContentType()))
                .contentLength(image.getImageSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }
}
