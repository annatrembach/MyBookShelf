package MyBookShelf.repository;

import MyBookShelf.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
    Optional<User> findByUserId(Long userId);
}
