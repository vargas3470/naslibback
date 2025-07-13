package nas.test.library.loans.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.*;
import nas.test.library.loans.domain.model.entities.Book;
import nas.test.library.loans.domain.model.entities.Borrower;
import nas.test.library.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.time.LocalDate;

@Entity
@Table(name = "loans")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Loan extends AuditableAbstractAggregateRoot<Loan> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Book book;

    @ManyToOne(optional = false)
    private Borrower borrower;

    @Column(nullable = false)
    private LocalDate loanDate;

    @Column(nullable = false)
    private LocalDate dueDate;

    private LocalDate returnDate;

    public boolean isActive() {
        return returnDate == null;
    }
    public Long getBookId() {
        return book != null ? book.getId() : null;
    }


}
