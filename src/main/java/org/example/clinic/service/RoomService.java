package org.example.clinic.service;

import lombok.RequiredArgsConstructor;
import org.example.clinic.dto.request.RoomCreateRequest;
import org.example.clinic.dto.request.RoomUpdateRequest;
import org.example.clinic.dto.response.RoomResponse;
import org.example.clinic.entity.Image;
import org.example.clinic.entity.Room;
import org.example.clinic.entity.RoomImage;
import org.example.clinic.repository.ImageRepository;
import org.example.clinic.repository.RoomImageRepository;
import org.example.clinic.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final ImageRepository imageRepository;
    private final RoomImageRepository roomImageRepository;

    public RoomResponse createRoom(RoomCreateRequest request) {
        Room room = new Room();
        room.setTitle(request.getTitle());
        room.setPrice(request.getPrice());
        room = roomRepository.save(room);

        List<String> imageUrls = room.getRoomImages().stream()
                .map(roomImage -> roomImage.getImage().getImgUrl())
                .toList();

        RoomResponse response = new RoomResponse();
        response.setId(room.getId());
        response.setTitle(room.getTitle());
        response.setPrice(room.getPrice());
        response.setImageUrls(imageUrls);
        return response;
    }

    public RoomResponse updateRoom(Integer roomId, RoomUpdateRequest request) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        room.setTitle(request.getTitle());
        room.setPrice(request.getPrice());
        room = roomRepository.save(room);

        // Fetch current image URLs associated with the room
        List<String> imageUrls = room.getRoomImages().stream()
                .map(roomImage -> roomImage.getImage().getImgUrl())
                .toList();

        RoomResponse response = new RoomResponse();
        response.setId(room.getId());
        response.setTitle(room.getTitle());
        response.setPrice(room.getPrice());
        response.setImageUrls(imageUrls);
        return response;
    }


    public void deleteRoom(Integer roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow();
        roomRepository.delete(room); // RoomImages will be deleted due to cascade
    }

    public List<String> getImageUrlsForRoom(Integer roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        return room.getRoomImages().stream()
                .map(roomImage -> roomImage.getImage().getImgUrl())
                .toList();
    }


}
