package MyBookShelf.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long Id_user;
    public String user_name;
    public String user_email;
    public String user_password;

    @OneToMany(mappedBy = "user")
    public List<Shelf> shelves;

    @OneToMany(mappedBy = "user")
    public List<Book_response> responses;

    @ManyToMany(mappedBy = "second_user")
    public List<User> first_user;

    @ManyToMany
    @JoinTable(
            name = "User_Connection",
            joinColumns = @JoinColumn(name = "Id_first_user"),
            inverseJoinColumns = @JoinColumn(name = "Id_second_user"))
    public List<User> second_user;

    public Long getId_user() {
        return Id_user;
    }

    public void setId_user(Long id_user) {
        Id_user = id_user;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public List<Shelf> getShelves() {
        return shelves;
    }

    public void setShelves(List<Shelf> shelves) {
        this.shelves = shelves;
    }

    public List<Book_response> getResponses() {
        return responses;
    }

    public void setResponses(List<Book_response> responses) {
        this.responses = responses;
    }

    public List<User> getFirst_user() {
        return first_user;
    }

    public void setFirst_user(List<User> first_user) {
        this.first_user = first_user;
    }

    public List<User> getSecond_user() {
        return second_user;
    }

    public void setSecond_user(List<User> second_user) {
        this.second_user = second_user;
    }

    public User() {
    }
}
