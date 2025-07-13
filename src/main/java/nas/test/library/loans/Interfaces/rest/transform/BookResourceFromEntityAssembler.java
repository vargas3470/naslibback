package nas.test.library.loans.Interfaces.rest.transform;

import nas.test.library.loans.domain.model.entities.Book;
import nas.test.library.loans.Interfaces.rest.resources.BookResource;

public class BookResourceFromEntityAssembler {

    public static BookResource toResource(Book book) {
        return new BookResource(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getPublisher(),
                book.getAmount(),
                book.getAmountBorrowed(),
                book.getAmountAvailable(),
                book.getImageId()
        );
    }
}
