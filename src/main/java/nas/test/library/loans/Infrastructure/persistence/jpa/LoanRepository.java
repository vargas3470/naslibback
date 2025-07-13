package nas.test.library.loans.Infrastructure.persistence.jpa;

import nas.test.library.loans.domain.model.aggregates.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByBorrowerDniAndReturnDateIsNull(String borrowerDni); // activos
    List<Loan> findByBook_Title(String title); // por título de libro
    List<Loan> findByBorrower_Dni(String dni);
}