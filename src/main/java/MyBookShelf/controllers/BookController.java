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
        model.addAttribute("bookName", "${el.shelfName}");
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
    public String bookPostAdd(@PathVariable("shelfName") @RequestParam String bookName, @RequestParam String bookShortDescription, @RequestParam Image bookCover, @RequestParam BookAuthor bookAuthor, @RequestParam BookPublisher bookPublisher, @RequestParam BookResponse bookResponse, Model model) {
        Book book = new Book(bookName, bookShortDescription, bookCover, bookAuthor, bookPublisher, bookResponse);
        bookRepository.save(book);
        return "redirect:/Shelf/{shelfName}/";
    }
}
