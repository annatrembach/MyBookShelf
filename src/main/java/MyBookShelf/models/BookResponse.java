package MyBookShelf.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class BookResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long Id_bookResponse;
    public String bookResponseText;
    public int bookResponseRating;
    public int bookResponseContentFactor;
    public int bookResponseFunFactor;
    public int bookResponseEmotionalFactor;
    public int bookResponseWowFactor;

    @OneToMany(mappedBy = "bookResponse")
    public List<Book> books;

    @ManyToOne
    @JoinColumn(name = "Id_user")
    public User user;

    public Long getId_bookResponse() {
        return Id_bookResponse;
    }
    public void setId_bookResponse(Long id_bookResponse) {
        Id_bookResponse = id_bookResponse;
    }

    public String getBookResponseText() {
        return bookResponseText;
    }
    public void setBookResponseText(String bookResponseText) {
        this.bookResponseText = bookResponseText;
    }

    public List<Book> getBooks() {
        return books;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public int getBookResponseWowFactor() {
        return bookResponseWowFactor;
    }
    public void setBookResponseWowFactor(int bookResponseWowFactor) {
        this.bookResponseWowFactor = bookResponseWowFactor;
    }

    public int getBookResponseRating() {
        return bookResponseRating;
    }
    public void setBookResponseRating(int bookResponseRating) {
        this.bookResponseRating = bookResponseRating;
    }

    public int getBookResponseContentFactor() {
        return bookResponseContentFactor;
    }
    public void setBookResponseContentFactor(int bookResponseContentFactor) {
        this.bookResponseContentFactor = bookResponseContentFactor;
    }

    public int getBookResponseFunFactor() {
        return bookResponseFunFactor;
    }
    public void setBookResponseFunFactor(int bookResponseFunFactor) {
        this.bookResponseFunFactor = bookResponseFunFactor;
    }

    public int getBookResponseEmotionalFactor() {
        return bookResponseEmotionalFactor;
    }
    public void setBookResponseEmotionalFactor(int bookResponseEmotionalFactor) {
        this.bookResponseEmotionalFactor = bookResponseEmotionalFactor;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public BookResponse() {
    }
}