package nas.test.library.loans.Interfaces.rest.exceptions;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
public class BorrowerNotFoundException extends RuntimeException {
    public BorrowerNotFoundException(String message) { super(message); }
}
