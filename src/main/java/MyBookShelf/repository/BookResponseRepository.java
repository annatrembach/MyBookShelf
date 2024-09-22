package MyBookShelf.repository;

import MyBookShelf.models.BookResponse;
import org.springframework.data.repository.CrudRepository;

public interface BookResponseRepository extends CrudRepository<BookResponse, Long> {
}
