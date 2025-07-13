package nas.test.library.loans.Infrastructure.persistence.jpa;

import nas.test.library.loans.domain.model.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String title);
}