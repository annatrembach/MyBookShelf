package MyBookShelf.repository;

import MyBookShelf.models.Shelf;
import org.springframework.data.repository.CrudRepository;

public interface ShelfRepository extends CrudRepository<Shelf, Long> {
    Shelf findByShelfName(String shelfName);
}