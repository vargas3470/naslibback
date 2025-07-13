package nas.test.library.loans.Interfaces.rest.transform;

import nas.test.library.loans.domain.model.entities.Borrower;
import nas.test.library.loans.Interfaces.rest.resources.BorrowerResource;

public class BorrowerResourceFromEntityAssembler {

    public static BorrowerResource toResource(Borrower borrower) {
        return new BorrowerResource(
                borrower.getId(),
                borrower.getDni(),
                borrower.getName(),
                borrower.getLastname(),
                borrower.getAddress()
        );
    }
}
