package MyBookShelf.repository;

import MyBookShelf.models.BookPublisher;
import org.springframework.data.repository.CrudRepository;

public interface BookPublisherRepository extends CrudRepository<BookPublisher, Long> {
}
