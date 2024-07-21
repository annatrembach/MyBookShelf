package MyBookShelf.controllers;

import MyBookShelf.models.*;
import MyBookShelf.repository.BookRepository;
import MyBookShelf.repository.ShelfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ShelfController {

    @Autowired
    public ShelfRepository shelfRepository;


    @GetMapping("/Shelves")
    public String allShelves(Model model) {
        this.shelfRepository = shelfRepository;
        Iterable<Shelf> shelves = shelfRepository.findAll();
        model.addAttribute("shelves", shelves);
        model.addAttribute("shelf_name", "My Shelves");
        return "Shelves";
    }
    @GetMapping("/Shelves/ShelfAdd")
    public String shelfAdd (Model model) { return "ShelfAdd"; }

    @PostMapping("/Shelves/ShelfAdd")
    public String shelfPostAdd(@RequestParam String shelf_name, @RequestParam User user){
        Shelf shelf = new Shelf(shelf_name, user);
        shelfRepository.save(shelf);
        return "redirect:/Shelves";
    }
}