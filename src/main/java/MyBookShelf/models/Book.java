package MyBookShelf.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id_book")
    public Long bookId;
    public String bookName;
    public String bookShortDescription;

    @ManyToOne
    @JoinColumn(name = "Id_bookAuthor")
    public BookAuthor bookAuthor;

    @ManyToOne
    @JoinColumn(name = "Id_bookPublisher")
    public BookPublisher bookPublisher;

    @ManyToOne
    @JoinColumn(name = "Id_bookResponse")
    public BookResponse bookResponse;

    @ManyToMany(mappedBy = "books")
    private List<Shelf> shelves;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Image bookCover;

    public Long getBookId() {
        return bookId;
    }
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookShortDescription() {
        return bookShortDescription;
    }
    public void setBookShortDescription(String bookShortDescription) {
        this.bookShortDescription = bookShortDescription;
    }

    public Image getBookCover() {
        return bookCover;
    }

    public void setBookCover(Image bookCover) {
        this.bookCover = bookCover;
    }

    public BookPublisher getBookPublisher() {
        return bookPublisher;
    }
    public void setBookPublisher(BookPublisher bookPublisher) {
        this.bookPublisher = bookPublisher;
    }

    public BookResponse getBookResponse() {
        return bookResponse;
    }
    public void setBookResponse(BookResponse bookResponse) {
        this.bookResponse = bookResponse;
    }

    public List<Shelf> getShelves() {
        return shelves;
    }
    public void setShelves(List<Shelf> shelves) {
        this.shelves = shelves;
    }

    public BookAuthor getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(BookAuthor bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public Book() {}

    public Book(String bookName, String bookShortDescription, BookAuthor bookAuthor, BookPublisher bookPublisher, BookResponse bookResponse) {
        this.bookName = bookName;
        this.bookShortDescription = bookShortDescription;
        this.bookAuthor = bookAuthor;
        this.bookPublisher = bookPublisher;
        this.bookResponse = bookResponse;
    }

    //For book cover
    public void addImageToBook(Image image) {
        image.setBook(this);
    }
}
