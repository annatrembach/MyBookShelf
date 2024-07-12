package MyBookShelf.models;

import jakarta.persistence.*;

import java.awt.*;
import java.util.List;

@Entity
public class Book_author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long Id_book_author;
    public String book_author_name;

    @OneToMany(mappedBy = "book_author")
    public List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Long getId_book_author() {
        return Id_book_author;
    }
    public void setId_book_author(Long id_book_author) {
        Id_book_author = id_book_author;
    }

    public String getBook_author_name() {
        return book_author_name;
    }
    public void setBook_author_name(String book_author_name) {
        this.book_author_name = book_author_name;
    }

    public Book_author() {
    }
}
