package nas.test.library.loans.Interfaces.rest.resources;

import java.time.LocalDate;

public record LoanResource(
        Long id,
        Long bookId,
        String borrowerDni,
        LocalDate loanDate,
        LocalDate dueDate,
        LocalDate returnDate
) {}
