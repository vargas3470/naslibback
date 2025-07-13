package nas.test.library.loans.Interfaces.rest;

import nas.test.library.loans.domain.model.entities.Borrower;
import nas.test.library.loans.Infrastructure.persistence.jpa.BorrowerRepository;
import nas.test.library.loans.Interfaces.rest.resources.BorrowerResource;
import nas.test.library.loans.Interfaces.rest.transform.BorrowerResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrowers")
public class BorrowerController {

    private final BorrowerRepository borrowerRepository;

    public BorrowerController(BorrowerRepository borrowerRepository) {
        this.borrowerRepository = borrowerRepository;
    }

    @GetMapping
    public ResponseEntity<List<BorrowerResource>> getAllBorrowers() {
        List<Borrower> borrowers = borrowerRepository.findAll();
        List<BorrowerResource> resources = borrowers.stream()
                .map(BorrowerResourceFromEntityAssembler::toResource)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{dni}")
    public ResponseEntity<BorrowerResource> getBorrowerByDni(@PathVariable String dni) {
        return borrowerRepository.findByDni(dni)
                .map(BorrowerResourceFromEntityAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<BorrowerResource> createBorrower(@RequestBody BorrowerResource resource) {
        Borrower borrower = new Borrower();
        borrower.setDni(resource.dni());
        borrower.setName(resource.name());
        borrower.setLastname(resource.lastname());
        borrower.setAddress(resource.address());
        Borrower saved = borrowerRepository.save(borrower);
        return ResponseEntity.ok(BorrowerResourceFromEntityAssembler.toResource(saved));
    }

    // PUT /borrowers/{id} - editar borrower
    @PutMapping("/{dni}")
    public ResponseEntity<BorrowerResource> updateBorrower(@PathVariable String dni, @RequestBody BorrowerResource resource) {
        return borrowerRepository.findByDni(dni)
                .map(borrower -> {
                    borrower.setDni(resource.dni());
                    borrower.setName(resource.name());
                    borrower.setLastname(resource.lastname());
                    borrower.setAddress(resource.address());
                    Borrower updated = borrowerRepository.save(borrower);
                    return ResponseEntity.ok(BorrowerResourceFromEntityAssembler.toResource(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
