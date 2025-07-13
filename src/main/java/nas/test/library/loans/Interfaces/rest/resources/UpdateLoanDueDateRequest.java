package nas.test.library.loans.Interfaces.rest.resources;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */

public record UpdateLoanDueDateRequest(
        Long loanId,
        String newDueDate // formato "yyyy-MM-dd"
) {}