package nas.test.library.loans.domain.services;

import nas.test.library.loans.domain.model.aggregates.Loan;
import nas.test.library.loans.domain.model.entities.Borrower;
import nas.test.library.loans.domain.model.queries.GetAvailableAmountByBookNameQuery;
import nas.test.library.loans.domain.model.queries.GetBorrowersByBookNameQuery;
import nas.test.library.loans.domain.model.queries.GetLoansByBorrowerQuery;

import java.util.List;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
public interface LoanQueryService {
    int handle(GetAvailableAmountByBookNameQuery query);
    List<Borrower> handle(GetBorrowersByBookNameQuery query);
    List<Loan> handle(GetLoansByBorrowerQuery query);
}
