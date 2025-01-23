package MyBookShelf.repository;

import MyBookShelf.models.BookPublisher;
import MyBookShelf.models.Image;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image, Long> {
}
