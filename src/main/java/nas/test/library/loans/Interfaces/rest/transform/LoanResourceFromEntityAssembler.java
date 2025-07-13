package nas.test.library.loans.Interfaces.rest.transform;

import nas.test.library.loans.domain.model.aggregates.Loan;
import nas.test.library.loans.Interfaces.rest.resources.LoanResource;
import nas.test.library.loans.domain.model.entities.Book;
import nas.test.library.loans.domain.model.entities.Borrower;

public class LoanResourceFromEntityAssembler {

        public static LoanResource toResource(Loan loan) {
            return new LoanResource(
                    loan.getId(),
                    loan.getBookId(),
                    loan.getBorrower().getDni(),
                    loan.getLoanDate(),
                    loan.getDueDate(),
                    loan.getReturnDate()
            );
        }

        public static Loan toEntity(LoanResource resource, Book book, Borrower borrower) {
            // Necesitas pasar las entidades porque Loan espera los objetos completos, no solo los IDs
            return Loan.builder()
                    .id(resource.id())
                    .book(book)
                    .borrower(borrower)
                    .loanDate(resource.loanDate())
                    .dueDate(resource.dueDate())
                    .returnDate(resource.returnDate())
                    .build();
        }
    }
