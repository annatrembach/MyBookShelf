package MyBookShelf.repository;

import MyBookShelf.models.Shelf;
import org.springframework.data.repository.CrudRepository;
import MyBookShelf.models.Book;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    Iterable<Book> findByShelf(Shelf shelf);
}