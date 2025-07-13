package nas.test.library.loans.application.internal.queryservices;

import nas.test.library.loans.domain.model.aggregates.Loan;
import nas.test.library.loans.domain.model.entities.Borrower;
import nas.test.library.loans.domain.model.queries.GetAvailableAmountByBookNameQuery;
import nas.test.library.loans.domain.model.queries.GetBorrowersByBookNameQuery;
import nas.test.library.loans.domain.model.queries.GetLoansByBorrowerQuery;
import nas.test.library.loans.domain.services.LoanQueryService;
import nas.test.library.loans.Infrastructure.persistence.jpa.BookRepository;
import nas.test.library.loans.Infrastructure.persistence.jpa.BorrowerRepository;
import nas.test.library.loans.Infrastructure.persistence.jpa.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LoanQueryServiceImpl implements LoanQueryService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final BorrowerRepository borrowerRepository;

    public LoanQueryServiceImpl(LoanRepository loanRepository, BookRepository bookRepository, BorrowerRepository borrowerRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.borrowerRepository = borrowerRepository;
    }

    @Override
    public int handle(GetAvailableAmountByBookNameQuery query) {
        return bookRepository.findByTitle(query.bookName())
                .map(book -> book.getAmount() - book.getAmountBorrowed())
                .orElse(0);
    }

    @Override
    public List<Borrower> handle(GetBorrowersByBookNameQuery query) {
        List<Loan> loans = loanRepository.findByBook_Title(query.bookName());
        // Solo los borrowers únicos (sin repetir)
        return loans.stream()
                .map(Loan::getBorrower)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<Loan> handle(GetLoansByBorrowerQuery query) {
        return loanRepository.findByBorrower_Dni(query.borrowerDni());
    }
}
