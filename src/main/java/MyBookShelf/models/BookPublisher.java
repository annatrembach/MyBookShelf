package MyBookShelf.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class BookPublisher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long Id_bookPublisher;
    public String bookPublisherName;

    @OneToMany(mappedBy = "bookPublisher")
    public List<Book> books;

    public Long getId_bookPublisher() {
        return Id_bookPublisher;
    }
    public void setId_bookPublisher(Long id_bookPublisher) {
        Id_bookPublisher = id_bookPublisher;
    }

    public String getBookPublisherName() {
        return bookPublisherName;
    }
    public void setBookPublisherName(String bookPublisherName) {
        this.bookPublisherName = bookPublisherName;
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
