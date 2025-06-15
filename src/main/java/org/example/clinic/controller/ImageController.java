package org.example.clinic.controller;

import lombok.RequiredArgsConstructor;
import org.example.clinic.dto.request.ImageUploadRequest;
import org.example.clinic.entity.Image;
import org.example.clinic.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping
    public ResponseEntity<Image> uploadImage(@RequestBody ImageUploadRequest request) {
        if (request.getRoomId() == null) {
            return ResponseEntity.badRequest().build(); // ensure roomId is present
        }
        return ResponseEntity.ok(imageService.uploadImage(request));
    }
}
