package nas.test.library.shared.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
@Entity
@Setter
@Getter
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String contentType;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] data;
}

