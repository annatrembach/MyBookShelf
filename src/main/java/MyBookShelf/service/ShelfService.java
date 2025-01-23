package MyBookShelf.service;

import MyBookShelf.models.Shelf;
import MyBookShelf.models.User;
import MyBookShelf.repository.ShelfRepository;
import MyBookShelf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class ShelfService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public ShelfRepository shelfRepository;

    public boolean addShelf(Shelf shelf) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = ((UserDetails) authentication.getPrincipal()).getUsername();
        User user = userRepository.findByEmail(email);
        shelf.setUser(user);
        shelfRepository.save(shelf);
        return true;
    }
}
