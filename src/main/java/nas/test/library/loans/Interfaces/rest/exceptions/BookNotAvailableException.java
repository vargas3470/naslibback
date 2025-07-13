package nas.test.library.loans.Interfaces.rest.exceptions;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
public class BookNotAvailableException extends RuntimeException {
    public BookNotAvailableException(String message) { super(message); }
}