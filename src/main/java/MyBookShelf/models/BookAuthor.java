package MyBookShelf.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class BookAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long bookAuthorId;
    public String bookAuthorName;

    @OneToMany(mappedBy = "bookAuthor")
    public List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Long getBookAuthorId() {
        return bookAuthorId;
    }
    public void setBookAuthorId(Long bookAuthorId) {
        this.bookAuthorId = bookAuthorId;
    }

    public String getBookAuthorName() {
        return bookAuthorName;
    }
    public void setBookAuthorName(String bookAuthorName) {
        this.bookAuthorName = bookAuthorName;
    }

    public BookAuthor() {
    }

}
