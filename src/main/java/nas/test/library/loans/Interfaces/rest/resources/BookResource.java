package nas.test.library.loans.Interfaces.rest.resources;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
public record BookResource(
        Long id,
        String title,
        String author,
        String isbn,
        String publisher,
        int amount,
        int amountBorrowed,
        int amountAvailable,
        String imageId        // referencia al recurso de imagen compartido
) {}
