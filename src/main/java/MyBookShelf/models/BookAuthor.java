package MyBookShelf.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class BookAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long Id_bookAuthor;
    public String bookAuthorName;

    @OneToMany(mappedBy = "bookAuthor")
    public List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Long getId_bookAuthor() {
        return Id_bookAuthor;
    }
    public void setId_bookAuthor(Long id_bookAuthor) {
        Id_bookAuthor = id_bookAuthor;
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
