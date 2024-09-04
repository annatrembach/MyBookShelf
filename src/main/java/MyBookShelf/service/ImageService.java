package MyBookShelf.service;

import MyBookShelf.models.Image;
import jakarta.transaction.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Transactional
public interface ImageService {
    Image saveImage(MultipartFile file) throws Exception;

    Image getImage(String fileId) throws Exception;
}
