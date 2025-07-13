package nas.test.library.loans.domain.services;

import nas.test.library.loans.domain.model.aggregates.Loan;
import nas.test.library.loans.domain.model.commands.BorrowBookCommand;
import nas.test.library.loans.domain.model.commands.ReturnBookCommand;

import java.util.Optional;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
public interface LoanCommandService {
    Optional<Loan> handle(ReturnBookCommand command);
    Optional<Loan> handle(BorrowBookCommand command);

}
