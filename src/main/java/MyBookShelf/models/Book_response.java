package MyBookShelf.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Book_response {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long Id_book_response;
    public String book_response_text;
    public int book_response_rating;
    public int book_response_content_factor;
    public int book_response_fun_factor;
    public int book_response_emotional_factor;
    public int book_response_wow_factor;

    @OneToMany(mappedBy = "response")
    public List<Book> books;

    @ManyToOne
    @JoinColumn(name = "Id_user", nullable = false)
    public User user;

    public Long getId_book_response() {
        return Id_book_response;
    }
    public void setId_book_response(Long id_book_response) {
        Id_book_response = id_book_response;
    }

    public String getBook_response_text() {
        return book_response_text;
    }
    public void setBook_response_text(String book_response_text) {
        this.book_response_text = book_response_text;
    }

    public List<Book> getBooks() {
        return books;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public int getBook_response_wow_factor() {
        return book_response_wow_factor;
    }
    public void setBook_response_wow_factor(int book_response_wow_factor) {
        this.book_response_wow_factor = book_response_wow_factor;
    }

    public int getBook_response_rating() {
        return book_response_rating;
    }
    public void setBook_response_rating(int book_response_rating) {
        this.book_response_rating = book_response_rating;
    }

    public int getBook_response_content_factor() {
        return book_response_content_factor;
    }
    public void setBook_response_content_factor(int book_response_content_factor) {
        this.book_response_content_factor = book_response_content_factor;
    }

    public int getBook_response_fun_factor() {
        return book_response_fun_factor;
    }
    public void setBook_response_fun_factor(int book_response_fun_factor) {
        this.book_response_fun_factor = book_response_fun_factor;
    }

    public int getBook_response_emotional_factor() {
        return book_response_emotional_factor;
    }
    public void setBook_response_emotional_factor(int book_response_emotional_factor) {
        this.book_response_emotional_factor = book_response_emotional_factor;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Book_response() {
    }
}
