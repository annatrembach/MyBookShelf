package MyBookShelf.repository;

import MyBookShelf.models.Image;
import MyBookShelf.models.User;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image, Long> {
    Image findByImageId(Long imageId);
}
