package MyBookShelf.controllers;

import MyBookShelf.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

@Controller
public class ImageController {

    @Autowired
    public ImageService imageService;

    @GetMapping("images/{userId}")
    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable Long userId) throws IOException {
        byte[] imageData = imageService.downloadImageFromFileSystem(userId);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }
}
