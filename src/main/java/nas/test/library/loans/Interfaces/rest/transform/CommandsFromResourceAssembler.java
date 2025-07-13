package nas.test.library.loans.Interfaces.rest.transform;

import nas.test.library.loans.domain.model.commands.BorrowBookCommand;
import nas.test.library.loans.domain.model.commands.ReturnBookCommand;
import nas.test.library.loans.Interfaces.rest.resources.BorrowBookRequest;
import nas.test.library.loans.Interfaces.rest.resources.ReturnBookRequest;

public class CommandsFromResourceAssembler {

    public static BorrowBookCommand toCommand(BorrowBookRequest resource) {
        return new BorrowBookCommand(
                resource.borrowerDni(),
                resource.bookId(),
                resource.days()
        );
    }

    public static ReturnBookCommand toCommand(ReturnBookRequest resource) {
        return new ReturnBookCommand(resource.loanId());
    }

}
