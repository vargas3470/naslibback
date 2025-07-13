package nas.test.library.shared.aplication.services;

import lombok.AllArgsConstructor;
import nas.test.library.shared.domain.model.entities.Image;
import nas.test.library.shared.domain.services.ImageService;
import nas.test.library.shared.infrastructure.persistence.jpa.repositories.repositories.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public UUID uploadImage(MultipartFile imageFile) throws IOException {
        Image image = new Image();
        image.setName(imageFile.getOriginalFilename());
        image.setData(imageFile.getBytes());
        image.setContentType(imageFile.getContentType());

        imageRepository.save(image);
        return image.getId();
    }

    @Override
    public Image getImageById(UUID imageId) {
        Image image = imageRepository.findById(imageId)
                .orElseThrow(()-> new RuntimeException("Image not found with id:"+imageId));
        return image;
    }

    @Override
    public void deleteImage(UUID imageId) {
        Image image = this.getImageById(imageId);
        imageRepository.delete(image);
    }
}
