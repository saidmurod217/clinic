package org.example.clinic.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "price")
    private Float price;

    @Column(name = "title", length = 100)
    private String title;

    @OneToMany(mappedBy = "room")
    private Set<ClinicRoom> clinicRooms = new LinkedHashSet<>();

    @OneToMany(mappedBy = "room")
    private Set<RoomImage> roomImages = new LinkedHashSet<>();

}