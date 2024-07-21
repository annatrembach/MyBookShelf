package MyBookShelf.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Shelf {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long Id_shelf;
    public String shelf_name;

    @ManyToOne
    @JoinColumn(name = "Id_user")
    public User user;

    @ManyToMany
    @JoinTable(
            name = "Shelf_Book",
            joinColumns = @JoinColumn(name = "Id_shelf"),
            inverseJoinColumns = @JoinColumn(name = "Id_book"))
    public List<Book> books;

    public Long getId_shelf() {
        return Id_shelf;
    }
    public void setId_shelf(Long id_shelf) {
        Id_shelf = id_shelf;
    }

    public String getShelf_name() {
        return shelf_name;
    }
    public void setShelf_name(String shelf_name) {
        this.shelf_name = shelf_name;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public List<Book> getBooks() {
        return books;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Shelf() {
    }

    public Shelf(String shelf_name, User user) {
        this.shelf_name = shelf_name;
        this.user = user;
    }
}
