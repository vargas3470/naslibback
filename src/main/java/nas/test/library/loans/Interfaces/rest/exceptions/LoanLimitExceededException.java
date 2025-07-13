package nas.test.library.loans.Interfaces.rest.exceptions;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
public class LoanLimitExceededException extends RuntimeException {
    public LoanLimitExceededException(String message) { super(message); }
}
