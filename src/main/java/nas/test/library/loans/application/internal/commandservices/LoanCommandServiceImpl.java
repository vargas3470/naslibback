package nas.test.library.loans.application.internal.commandservices;

import nas.test.library.loans.domain.model.aggregates.Loan;
import nas.test.library.loans.domain.model.commands.BorrowBookCommand;
import nas.test.library.loans.domain.model.commands.ReturnBookCommand;
import nas.test.library.loans.domain.model.entities.Book;
import nas.test.library.loans.domain.model.entities.Borrower;
import nas.test.library.loans.domain.services.LoanCommandService;
import nas.test.library.loans.Infrastructure.persistence.jpa.BookRepository;
import nas.test.library.loans.Infrastructure.persistence.jpa.BorrowerRepository;
import nas.test.library.loans.Infrastructure.persistence.jpa.LoanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LoanCommandServiceImpl implements LoanCommandService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final BorrowerRepository borrowerRepository;

    public LoanCommandServiceImpl(LoanRepository loanRepository, BookRepository bookRepository, BorrowerRepository borrowerRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.borrowerRepository = borrowerRepository;
    }

    @Override
    @Transactional
    public Optional<Loan> handle(BorrowBookCommand command) {
        Optional<Book> bookOpt = bookRepository.findById(command.bookId());
        Optional<Borrower> borrowerOpt = borrowerRepository.findByDni(command.borrowerDni());

        if (bookOpt.isEmpty() || borrowerOpt.isEmpty()) return Optional.empty();

        Book book = bookOpt.get();
        Borrower borrower = borrowerOpt.get();

        // Verificar límite de préstamos activos
        List<Loan> activeLoans = loanRepository.findByBorrowerDniAndReturnDateIsNull(borrower.getDni());
        if (activeLoans.size() >= 15) return Optional.empty();

        // Verificar disponibilidad de ejemplares
        if (!book.canBeBorrowed()) return Optional.empty();

        // Actualizar libro (cantidad prestada)
        book.incrementAmountBorrowed();
        bookRepository.save(book);

        // Crear préstamo
        LocalDate loanDate = LocalDate.now();
        LocalDate dueDate = loanDate.plusDays(command.days());

        Loan loan = Loan.builder()
                .book(book)
                .borrower(borrower)
                .loanDate(loanDate)
                .dueDate(dueDate)
                .build();

        Loan savedLoan = loanRepository.save(loan);
        return Optional.of(savedLoan);
    }

    @Override
    @Transactional
    public Optional<Loan> handle(ReturnBookCommand command) {
        Optional<Loan> loanOpt = loanRepository.findById(command.loanId());
        if (loanOpt.isEmpty()) return Optional.empty();

        Loan loan = loanOpt.get();
        if (!loan.isActive()) return Optional.empty();

        Book book = loan.getBook();
        book.decrementAmountBorrowed();
        bookRepository.save(book);

        loan.setReturnDate(LocalDate.now());
        Loan updatedLoan = loanRepository.save(loan);

        return Optional.of(updatedLoan);
    }
}
