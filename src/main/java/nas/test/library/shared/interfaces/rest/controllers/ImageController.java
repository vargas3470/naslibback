package nas.test.library.shared.interfaces.rest.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import nas.test.library.shared.domain.model.entities.Image;
import nas.test.library.shared.domain.services.ImageService;
import nas.test.library.shared.interfaces.rest.resources.ImageResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * @author Ariana Vargas Revollé - U20221a928
 * @version 1.0
 */
@Tag(name="Images", description = "Images Management Endpoints")
@RestController
@RequestMapping("/api/v1/images")
@AllArgsConstructor
public class ImageController {
    private ImageService imageService;

    @Operation(summary = "Create a new image")
    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ImageResource> uploadImage(@RequestParam("file") MultipartFile imageFile) throws IOException {
        UUID imageId = imageService.uploadImage(imageFile);
        return ResponseEntity.ok(new ImageResource(imageId));
    }

    @Operation(summary = "Get an image by ID")
    @GetMapping("/{imageId}")
    public ResponseEntity<byte[]> getImageById(@PathVariable UUID imageId) {
        Image image = imageService.getImageById(imageId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(image.getContentType())); // O PNG, según corresponda
        headers.setContentDisposition(ContentDisposition.inline()
                .filename(image.getName())
                .build());

        return new ResponseEntity<>(image.getData(), headers, HttpStatus.OK);
    }

    @Operation(summary = "Delete an image by ID")
    @DeleteMapping("/{imageId}")
    public ResponseEntity<?> deleteImageById(@PathVariable UUID imageId) {
        imageService.deleteImage(imageId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
