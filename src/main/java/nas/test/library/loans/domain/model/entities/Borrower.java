package nas.test.library.loans.domain.model.entities;

import jakarta.persistence.*;
import lombok.*;
import nas.test.library.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
@Table(name = "borrowers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Borrower extends AuditableAbstractAggregateRoot<Borrower> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 15)
    private String dni;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String lastname;

    @Column(nullable = false, length = 150)
    private String address;
}
