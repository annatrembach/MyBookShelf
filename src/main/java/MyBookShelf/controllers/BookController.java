package MyBookShelf.controllers;

import MyBookShelf.models.*;
import MyBookShelf.repository.BookRepository;
import MyBookShelf.repository.ShelfRepository;
import MyBookShelf.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
public class BookController {

    @Autowired
    public BookRepository bookRepository;

    @Autowired
    public ShelfRepository shelfRepository;

    @Autowired
    public BookService bookService;

    @GetMapping("/Shelf/{shelfName}")
    public String shelfsBooks(@PathVariable("shelfName") String shelfName, Model model) {
        Shelf shelf = shelfRepository.findByShelfName(shelfName);
        Iterable<Book> books = bookRepository.findByShelves(shelf);
        model.addAttribute("shelfName", shelfName);
        model.addAttribute("books", books);
        return "Shelf";
    }

    @GetMapping("/Shelf/{shelfName}/AddBook")
    public String bookAdd(@PathVariable("shelfName") String shelfName, Model model) {
        model.addAttribute("shelfName", shelfName);
        return "BookAdd";
    }

    @PostMapping("/Shelf/{shelfName}/AddBook")
    public String bookPostAdd(Book book, @RequestParam("file") MultipartFile file) throws IOException {
        bookService.addBook(book, file);
        return "redirect:/Shelf/{shelfName}/";
    }

    @GetMapping("/Shelf/{shelfName}/{bookName}")
    public String bookInfo(@PathVariable String bookName, Model model) {
        Book book = bookRepository.findBybookName(bookName);
        model.addAttribute("book", book);
        model.addAttribute("image", book.getBookCover());
        return "Book";
    }
}
