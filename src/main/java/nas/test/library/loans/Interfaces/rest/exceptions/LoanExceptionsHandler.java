
package nas.test.library.loans.Interfaces.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class LoanExceptionsHandler {

    @ExceptionHandler(BookNotAvailableException.class)
    public ResponseEntity<Map<String, String>> handle(BookNotAvailableException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Conflict");
        error.put("message", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BorrowerNotFoundException.class)
    public ResponseEntity<Map<String, String>> handle(BorrowerNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Not Found");
        error.put("message", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LoanLimitExceededException.class)
    public ResponseEntity<Map<String, String>> handle(LoanLimitExceededException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Conflict");
        error.put("message", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(LoanNotFoundException.class)
    public ResponseEntity<Map<String, String>> handle(LoanNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Not Found");
        error.put("message", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Puedes agregar otros @ExceptionHandler según lo necesites
}
