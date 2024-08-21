package MyBookShelf.repository;

import MyBookShelf.models.Book;
import MyBookShelf.models.BookAuthor;
import org.springframework.data.repository.CrudRepository;

public interface BookAuthorRepository extends CrudRepository<BookAuthor, Long> {
    //BookAuthor findByBook (Book book);
    //BookAuthor findByBookName (String bookName);
}
