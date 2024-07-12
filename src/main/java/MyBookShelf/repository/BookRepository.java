package MyBookShelf.repository;

import org.springframework.data.repository.CrudRepository;
import MyBookShelf.models.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
}
