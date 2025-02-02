package MyBookShelf.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Shelf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long Id_shelf;
    public String shelfName;

    @ManyToOne
    @JoinColumn(name = "userId")
    public User user;

    @OneToMany
    public List<Book> books;

    public Long getId_shelf() {
        return Id_shelf;
    }
    public void setId_shelf(Long id_shelf) {
        Id_shelf = id_shelf;
    }

    public String getShelfName() {
        return shelfName;
    }
    public void setShelfName(String shelfName) {
        this.shelfName = shelfName;
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

    public Shelf(String shelfName, User user) {
        this.shelfName = shelfName;
        this.user = user;
    }
}
