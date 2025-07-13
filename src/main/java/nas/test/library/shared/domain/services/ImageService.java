package nas.test.library.shared.domain.services;

import nas.test.library.shared.domain.model.entities.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
public interface ImageService {
    public UUID uploadImage(MultipartFile imageFile) throws IOException;
    public Image getImageById(UUID imageId);
    public void deleteImage(UUID imageId);
}