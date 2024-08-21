package MyBookShelf.repository;

import MyBookShelf.models.Book;
import MyBookShelf.models.BookResponse;
import org.springframework.data.repository.CrudRepository;

public interface BookResponseRepository extends CrudRepository<BookResponse, Long> {
    //Iterable<BookResponse> findByBook (Book book);
}
