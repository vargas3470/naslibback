package nas.test.library.loans.Interfaces.rest;

import nas.test.library.loans.domain.model.aggregates.Loan;
import nas.test.library.loans.domain.services.LoanCommandService;
import nas.test.library.loans.domain.services.LoanQueryService;
import nas.test.library.loans.Interfaces.rest.resources.*;
import nas.test.library.loans.Interfaces.rest.transform.*;
import nas.test.library.loans.domain.model.commands.BorrowBookCommand;
import nas.test.library.loans.domain.model.commands.ReturnBookCommand;
import nas.test.library.loans.domain.model.queries.GetAvailableAmountByBookNameQuery;
import nas.test.library.loans.domain.model.queries.GetBorrowersByBookNameQuery;
import nas.test.library.loans.domain.model.queries.GetLoansByBorrowerQuery;
import nas.test.library.loans.domain.model.entities.Borrower;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import nas.test.library.loans.Infrastructure.persistence.jpa.LoanRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanCommandService loanCommandService;
    private final LoanQueryService loanQueryService;
    private final LoanRepository loanRepository;

    public LoanController(LoanRepository loanRepository,
                          LoanCommandService loanCommandService,
                          LoanQueryService loanQueryService) {
        this.loanRepository = loanRepository;
        this.loanCommandService = loanCommandService;
        this.loanQueryService = loanQueryService;
    }

    // Prestar libro
    @PostMapping("/borrow")
    public ResponseEntity<LoanResource> borrowBook(@RequestBody BorrowBookRequest request) {
        BorrowBookCommand command = CommandsFromResourceAssembler.toCommand(request);
        Optional<Loan> loanOpt = loanCommandService.handle(command);
        return loanOpt
                .map(LoanResourceFromEntityAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    // Devolver libro
    @PostMapping("/return")
    public ResponseEntity<LoanResource> returnBook(@RequestBody ReturnBookRequest request) {
        ReturnBookCommand command = CommandsFromResourceAssembler.toCommand(request);
        Optional<Loan> loanOpt = loanCommandService.handle(command);
        return loanOpt
                .map(LoanResourceFromEntityAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    // Consultar préstamos por borrower
    @GetMapping("/by-borrower/{borrowerDni}")
    public ResponseEntity<List<LoanResource>> getLoansByBorrower(@PathVariable String borrowerDni) {
        List<Loan> loans = loanQueryService.handle(new GetLoansByBorrowerQuery(borrowerDni));
        List<LoanResource> resources = loans.stream()
                .map(LoanResourceFromEntityAssembler::toResource)
                .toList();
        return ResponseEntity.ok(resources);
    }

    // Consultar cantidad disponible de un libro por nombre
    @GetMapping("/available-amount")
    public ResponseEntity<Integer> getAvailableAmountByBookName(@RequestParam String bookName) {
        int available = loanQueryService.handle(new GetAvailableAmountByBookNameQuery(bookName));
        return ResponseEntity.ok(available);
    }

    // Consultar lista de borrowers que han tomado un libro (por nombre de libro)
    @GetMapping("/borrowers-by-book")
    public ResponseEntity<List<BorrowerResource>> getBorrowersByBookName(@RequestParam String bookName) {
        List<Borrower> borrowers = loanQueryService.handle(new GetBorrowersByBookNameQuery(bookName));
        List<BorrowerResource> resources = borrowers.stream()
                .map(BorrowerResourceFromEntityAssembler::toResource)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable Long id) {
        if (!loanRepository.existsById(id))
            return ResponseEntity.notFound().build();
        loanRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}/due-date")
    public ResponseEntity<LoanResource> updateLoanDueDate(@PathVariable Long id, @RequestBody UpdateLoanDueDateRequest request) {
        return loanRepository.findById(id)
                .map(loan -> {
                    loan.setDueDate(LocalDate.parse(request.newDueDate())); // Formato: yyyy-MM-dd
                    Loan updated = loanRepository.save(loan);
                    return ResponseEntity.ok(LoanResourceFromEntityAssembler.toResource(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
