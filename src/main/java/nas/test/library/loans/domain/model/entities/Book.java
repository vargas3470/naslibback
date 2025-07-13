package nas.test.library.loans.domain.model.entities;

import jakarta.persistence.*;
import lombok.*;
import nas.test.library.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book extends AuditableAbstractAggregateRoot<Book> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 150)
    private String author;

    @Column(nullable = false, length = 30)
    private String isbn;

    @Column(nullable = false, length = 50)
    private String publisher;

    @Column(nullable = false)
    private int amount;  // Total copies

    @Column(nullable = false)
    private int amountBorrowed; // Currently borrowed

    @Column(name = "image_id")
    private String imageId; // Reference to shared image

    public int getAmountAvailable() {
        return amount - amountBorrowed;
    }

    public boolean canBeBorrowed() {
        return getAmountAvailable() > 0;
    }

    public void incrementAmountBorrowed() {
        if (!canBeBorrowed()) throw new IllegalStateException("No copies available.");
        this.amountBorrowed++;
    }

    public void decrementAmountBorrowed() {
        if (amountBorrowed > 0) this.amountBorrowed--;
    }

    public void setAmountBorrowed(int amountBorrowed) {
        this.amountBorrowed = amountBorrowed;
    }
}
