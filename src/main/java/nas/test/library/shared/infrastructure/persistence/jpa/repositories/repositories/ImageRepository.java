package nas.test.library.shared.infrastructure.persistence.jpa.repositories.repositories;
import nas.test.library.shared.domain.model.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
@Repository
public interface ImageRepository extends JpaRepository<Image, UUID> {
}
