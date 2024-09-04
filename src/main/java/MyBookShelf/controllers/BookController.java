package MyBookShelf.controllers;

import MyBookShelf.models.*;
import MyBookShelf.repository.BookRepository;
import MyBookShelf.repository.ShelfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {

    @Autowired
    public BookRepository bookRepository;

    @Autowired
    public ShelfRepository shelfRepository;

    @GetMapping("/Shelf/{shelfName}")
    public String shelfsBooks(@PathVariable("shelfName") String shelfName, Model model) {
        Shelf shelf = shelfRepository.findByshelfName(shelfName);
        /*if (shelf != null) {*/
            Iterable<Book> books = bookRepository.findByShelves(shelf);
            model.addAttribute("shelfName", shelfName);
            model.addAttribute("books", books);
            return "Shelf";
        /*} else {
            return "redirect:/Shelves";
        }*/
    }
    /*
    @GetMapping("/Shelf/{shelfName}")
    public String shelfsBooks(Model model) {
        this.bookRepository = bookRepository;
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        model.addAttribute("book_name", "${el.shelfName}");
        return "Shelf";
    }
*/
    @GetMapping("/Shelf/{shelfName}/AddBook")
    public String bookAdd(@PathVariable("shelfName") String shelfName, Model model) {
        model.addAttribute("shelfName", shelfName);
        return "BookAdd";
    }
    /*
    @GetMapping("/Shelf/Add Book")
    public String bookAdd(Model model) {
        return "BookAdd";
    }
*/
    @PostMapping("/Shelf/{shelfName}/AddBook")
    public String bookPostAdd(@PathVariable("shelfName") @RequestParam String book_name, @RequestParam String book_short_description, @RequestParam byte[] book_cover, @RequestParam Book_author book_author, @RequestParam Book_publisher book_publisher, @RequestParam Book_response book_response, Model model) {
        Book book = new Book(book_name, book_short_description, book_cover, book_author, book_publisher, book_response);
        bookRepository.save(book);
        return "redirect:/Shelf/{shelfName}/";
    }
}
