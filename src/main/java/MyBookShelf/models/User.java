package MyBookShelf.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "Users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id_user")
    public Long userId;
    public String username;
    public String email;
    public String password;
    public boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "Id_user"))
    @Enumerated(EnumType.STRING)
    public Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user")
    public List<Shelf> shelves;

    @OneToMany(mappedBy = "user")
    public List<BookResponse> responses;

    @ManyToMany(mappedBy = "second_user")
    public List<User> first_user;

    @ManyToMany
    @JoinTable(
            name = "User_Connection",
            joinColumns = @JoinColumn(name = "Id_first_user"),
            inverseJoinColumns = @JoinColumn(name = "Id_second_user"))
    public List<User> second_user;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Image userPicture;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Shelf> getShelves() {
        return shelves;
    }

    public void setShelves(List<Shelf> shelves) {
        this.shelves = shelves;
    }

    public List<BookResponse> getResponses() {
        return responses;
    }

    public void setResponses(List<BookResponse> responses) {
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Image getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(Image userPicture) {
        this.userPicture = userPicture;
        userPicture.setUser(this);
    }

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

}
