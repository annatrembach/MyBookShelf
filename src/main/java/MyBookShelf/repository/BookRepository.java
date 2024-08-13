package MyBookShelf.repository;

import MyBookShelf.models.Shelf;
import org.springframework.data.repository.CrudRepository;
import MyBookShelf.models.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
    Iterable<Book> findByShelves(Shelf shelf);
}
