package org.example.clinic.service;

import lombok.RequiredArgsConstructor;
import org.example.clinic.dto.request.ImageUploadRequest;
import org.example.clinic.entity.Image;
import org.example.clinic.entity.Room;
import org.example.clinic.entity.RoomImage;
import org.example.clinic.repository.ImageRepository;
import org.example.clinic.repository.RoomImageRepository;
import org.example.clinic.repository.RoomRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final RoomRepository roomRepository;
    private final RoomImageRepository roomImageRepository;

    public Image uploadImage(ImageUploadRequest request) {
        Image image = new Image();
        image.setImgUrl(request.getImgUrl());
        image = imageRepository.save(image);

        if (request.getRoomId() != null) {
            Room room = roomRepository.findById(request.getRoomId())
                    .orElseThrow(() -> new RuntimeException("Room not found"));

            RoomImage roomImage = new RoomImage();
            roomImage.setImage(image);
            roomImage.setRoom(room);
            roomImageRepository.save(roomImage);
        }

        return image;
    }
}
