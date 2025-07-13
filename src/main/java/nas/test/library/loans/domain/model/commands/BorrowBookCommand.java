package nas.test.library.loans.domain.model.commands;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
public record BorrowBookCommand(String borrowerDni, Long bookId, int days) {}

