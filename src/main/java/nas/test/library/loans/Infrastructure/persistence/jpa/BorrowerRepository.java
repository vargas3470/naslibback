package nas.test.library.loans.Infrastructure.persistence.jpa;

import nas.test.library.loans.domain.model.entities.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
public interface BorrowerRepository extends JpaRepository<Borrower, Long> {
    Optional<Borrower> findByDni(String dni);
}