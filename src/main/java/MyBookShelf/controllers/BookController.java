package MyBookShelf.controllers;

import MyBookShelf.models.*;
import MyBookShelf.repository.BookAuthorRepository;
import MyBookShelf.repository.BookPublisherRepository;
import MyBookShelf.repository.BookRepository;
import MyBookShelf.repository.ShelfRepository;
import MyBookShelf.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class BookController {

    @Autowired
    public BookRepository bookRepository;

    @Autowired
    public ShelfRepository shelfRepository;

    @Autowired
    public BookAuthorRepository bookAuthorRepository;

    @Autowired
    public BookPublisherRepository bookPublisherRepository;

    @Autowired
    public ImageService imageService;

    @GetMapping("/Shelf/{shelfName}")
    public String shelfsBooks(@PathVariable("shelfName") String shelfName, Model model) {
        Shelf shelf = shelfRepository.findByShelfName(shelfName);
        Iterable<Book> books = bookRepository.findByShelf(shelf);
        model.addAttribute("shelfName", shelfName);
        model.addAttribute("books", books);
        System.out.println(books);
        return "Shelf";
    }

    @GetMapping("/Shelf/{shelfName}/AddBook")
    public String addBook(@PathVariable String shelfName, Model model) {
        Iterable<BookAuthor> bookAuthors = bookAuthorRepository.findAll();
        Iterable<BookPublisher> bookPublishers = bookPublisherRepository.findAll();

        model.addAttribute("shelfName", shelfName);
        model.addAttribute("bookAuthors", bookAuthors);
        model.addAttribute("bookPublishers", bookPublishers);

        return "AddBook";
    }

    @PostMapping("/Shelf/{shelfName}/AddBook")
    public String addBookPost(
            @PathVariable String shelfName,
            @RequestParam String bookName,
            @RequestParam MultipartFile bookCover,
            @RequestParam(required = false) Long bookAuthorId,
            @RequestParam(required = false) String bookAuthorName,
            @RequestParam(required = false) Long bookPublisherId,
            @RequestParam(required = false) String bookPublisherName,
            @RequestParam String bookShortDescription,
            @RequestParam(required = false) int bookResponse) throws IOException {

        // Обробка автора
        BookAuthor bookAuthor = null;
        if (bookAuthorId != null) {
            bookAuthor = bookAuthorRepository.findById(bookAuthorId)
                    .orElseThrow(() -> new IllegalArgumentException("Author not found"));
        } else if (bookAuthorName != null) {
            bookAuthor = new BookAuthor();
            bookAuthor.setBookAuthorName(bookAuthorName);
            bookAuthor = bookAuthorRepository.save(bookAuthor);
        }

        // Обробка видавця
        BookPublisher bookPublisher = null;
        if (bookPublisherId != null) {
            bookPublisher = bookPublisherRepository.findById(bookPublisherId)
                    .orElseThrow(() -> new IllegalArgumentException("Publisher not found"));
        } else if (bookPublisherName != null) {
            bookPublisher = new BookPublisher();
            bookPublisher.setBookPublisherName(bookPublisherName);
            bookPublisher = bookPublisherRepository.save(bookPublisher);
        }

        // Створення нової книги
        Book book = new Book();
        book.setBookName(bookName);
        if (!bookCover.isEmpty()) {
            Image image = imageService.uploadImageToFileSystem(bookCover);
            book.setBookCover(image);
        }
        book.setBookAuthor(bookAuthor);
        book.setBookPublisher(bookPublisher);
        book.setBookShortDescription(bookShortDescription);
        book.setShelf(shelfRepository.findByShelfName(shelfName));

        bookRepository.save(book);

        return "redirect:/Shelf/{shelfName}";
    }

}
