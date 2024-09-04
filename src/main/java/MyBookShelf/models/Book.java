package MyBookShelf.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long Id_book;
    public String book_name;
    public String book_short_description;

    @Lob
    public byte[] book_cover;

    @ManyToOne
    @JoinColumn(name = "Id_book_author")
    public Book_author book_author;

    @ManyToOne
    @JoinColumn(name = "Id_book_publisher")
    public Book_publisher book_publisher;

    @ManyToOne
    @JoinColumn(name = "Id_book_response")
    public Book_response book_response;

    @ManyToMany(mappedBy = "books")
    private List<Shelf> shelves;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Image bookCover;

    public Long getId_book() {
        return Id_book;
    }
    public void setId_book(Long id_book) {
        Id_book = id_book;
    }

    public String getBook_name() {
        return book_name;
    }
    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_short_description() {
        return book_short_description;
    }
    public void setBook_short_description(String book_short_description) {
        this.book_short_description = book_short_description;
    }

    public byte[] getBook_cover() {
        return book_cover;
    }
    public void setBook_cover(byte[] book_cover) {
        this.book_cover = book_cover;
    }

    public Book_author getBook_author() {
        return book_author;
    }
    public void setBook_author(Book_author book_author) {
        this.book_author = book_author;
    }

    public Book_publisher getBook_publisher() {
        return book_publisher;
    }
    public void setBook_publisher(Book_publisher book_publisher) {
        this.book_publisher = book_publisher;
    }

    public Book_response getBook_response() {
        return book_response;
    }
    public void setBook_response(Book_response book_response) {
        this.book_response = book_response;
    }

    public List<Shelf> getShelves() {
        return shelves;
    }
    public void setShelves(List<Shelf> shelves) {
        this.shelves = shelves;
    }

    public Book() {}

    public Book(String book_name, String book_short_description, byte[] book_cover, Book_author book_author, Book_publisher book_publisher, Book_response book_response) {
        this.book_name = book_name;
        this.book_short_description = book_short_description;
        this.book_cover = book_cover;
        this.book_author = book_author;
        this.book_publisher = book_publisher;
        this.book_response = book_response;
    }
}
