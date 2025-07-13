package nas.test.library.loans.Interfaces.rest.resources;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
public record BorrowBookRequest(
        String borrowerDni,
        Long bookId,
        int days
) {}