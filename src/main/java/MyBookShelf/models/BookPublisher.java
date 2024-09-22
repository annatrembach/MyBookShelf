package MyBookShelf.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class BookPublisher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long Id_book_publisher;
    public String book_publisher_name;

    @OneToMany(mappedBy = "bookPublisher")
    public List<Book> books;

    public Long getId_book_publisher() {
        return Id_book_publisher;
    }
    public void setId_book_publisher(Long id_book_publisher) {
        Id_book_publisher = id_book_publisher;
    }

    public String getBook_publisher_name() {
        return book_publisher_name;
    }
    public void setBook_publisher_name(String book_publisher_name) {
        this.book_publisher_name = book_publisher_name;
    }

    public List<Book> getBooks() {
        return books;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public BookPublisher() {
    }
}
