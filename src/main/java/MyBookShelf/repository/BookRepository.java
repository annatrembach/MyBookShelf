package MyBookShelf.repository;

import MyBookShelf.models.Shelf;
import org.springframework.data.repository.CrudRepository;
import MyBookShelf.models.Book;

import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {
    Iterable<Book> findByShelves(Shelf shelf);
    Book findBybookName(String bookName);

    //Iterable<Book> findByBookAuthorName(String bookAuthorName);
    //Iterable<Book> findByBookPublisherName(String bookPublisherName);
    //Book findByBookName(String bookName);
}
