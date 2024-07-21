package MyBookShelf.controllers;

import MyBookShelf.models.*;
import MyBookShelf.repository.BookRepository;
import MyBookShelf.repository.ShelfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class BookController {

    @Autowired
    public BookRepository bookRepository;

    @GetMapping("/Shelf")
    public String shelfsBooks(Model model) {
        this.bookRepository = bookRepository;
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        model.addAttribute("book_name", "${el.shelf_name}");
        return "Shelf";
    }

    @GetMapping("/Shelf/Add Book")
    public String bookAdd(Model model) {
        return "BookAdd";
    }

    @PostMapping("/Shelf/Add Book")
    public String bookPostAdd(@RequestParam String book_name, @RequestParam String book_short_description, @RequestParam byte[] book_cover, @RequestParam Book_author book_author, @RequestParam Book_publisher book_publisher, @RequestParam Book_response book_response, Model model) {
        Book book = new Book(book_name, book_short_description, book_cover, book_author, book_publisher, book_response);
        bookRepository.save(book);
        return "redirect:/Shelves";
    }
}
