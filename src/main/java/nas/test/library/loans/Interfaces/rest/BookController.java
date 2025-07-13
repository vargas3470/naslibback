package nas.test.library.loans.Interfaces.rest;

import nas.test.library.loans.domain.model.entities.Book;
import nas.test.library.loans.Infrastructure.persistence.jpa.BookRepository;
import nas.test.library.loans.Interfaces.rest.resources.BookResource;
import nas.test.library.loans.Interfaces.rest.transform.BookResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public ResponseEntity<List<BookResource>> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookResource> resources = books.stream()
                .map(BookResourceFromEntityAssembler::toResource)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResource> getBookById(@PathVariable Long id) {
        return bookRepository.findById(id)
                .map(BookResourceFromEntityAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<BookResource> createBook(@RequestBody BookResource resource) {
        Book book = new Book();
        book.setTitle(resource.title());
        book.setAuthor(resource.author());
        book.setIsbn(resource.isbn());
        book.setPublisher(resource.publisher());
        book.setAmount(resource.amount());
        book.setImageId(resource.imageId());
        book.setAmountBorrowed(0);

        Book saved = bookRepository.save(book);
        return ResponseEntity.ok(BookResourceFromEntityAssembler.toResource(saved));
    }



    // PUT /books/{id} - editar libro
    @PutMapping("/{id}")
    public ResponseEntity<BookResource> updateBook(@PathVariable Long id, @RequestBody BookResource resource) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(resource.title());
                    book.setAuthor(resource.author());
                    book.setIsbn(resource.isbn());
                    book.setPublisher(resource.publisher());
                    book.setAmount(resource.amount());
                    book.setAmountBorrowed(resource.amountBorrowed());
                    // setImageId si corresponde
                    Book updated = bookRepository.save(book);
                    return ResponseEntity.ok(BookResourceFromEntityAssembler.toResource(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (!bookRepository.existsById(id))
            return ResponseEntity.notFound().build();
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
