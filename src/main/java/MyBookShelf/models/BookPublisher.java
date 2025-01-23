package MyBookShelf.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class BookPublisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long bookPublisherId;
    public String bookPublisherName;

    @OneToMany(mappedBy = "bookPublisher")
    public List<Book> books;

    public Long getBookPublisherId() {
        return bookPublisherId;
    }
    public void setBookPublisherId(Long bookPublisherId) {
        this.bookPublisherId = bookPublisherId;
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
