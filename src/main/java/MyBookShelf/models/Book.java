package MyBookShelf.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long bookId;
    public String bookName;
    public String bookShortDescription;

    @ManyToOne
    @JoinColumn(name = "bookAuthorId")
    public BookAuthor bookAuthor;

    @ManyToOne
    @JoinColumn(name = "bookPublisherId")
    public BookPublisher bookPublisher;

    @ManyToOne
    @JoinColumn(name = "bookResponseId")
    public BookResponse bookResponse;

    @ManyToMany(mappedBy = "books")
    private List<Shelf> shelves;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "imageId")
    public Image bookCover;

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

    public Image getBookCover() {
        return bookCover;
    }
    public void setBookCover(Image bookCover) {
        this.bookCover = bookCover;
    }

    public Book() {}

    public Book(String bookName, String bookShortDescription, Image bookCover, BookAuthor bookAuthor, BookPublisher bookPublisher, BookResponse bookResponse) {
        this.bookName = bookName;
        this.bookShortDescription = bookShortDescription;
        this.bookCover = bookCover;
        this.bookAuthor = bookAuthor;
        this.bookPublisher = bookPublisher;
        this.bookResponse = bookResponse;
    }
}
