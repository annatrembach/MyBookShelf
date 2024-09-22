package MyBookShelf.service;

import MyBookShelf.models.Image;
import MyBookShelf.repository.ImageRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Image saveImage(MultipartFile file) throws Exception {
        String imageName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if(imageName.contains("..")) {
                throw new Exception("Filename contains invalid path sequence "
                        + imageName);
            }

            Image image
                    = new Image(imageName,
                    file.getContentType(),
                    file.getBytes());
            return imageRepository.save(image);

        } catch (Exception e) {
            throw new Exception("Could not save File: " + imageName);
        }
    }

    @Override
    public Image getImage(String Id_image) throws Exception {
        return imageRepository
                .findById(Id_image)
                .orElseThrow(
                        () -> new Exception("File not found with Id: " + Id_image));
    }
}
