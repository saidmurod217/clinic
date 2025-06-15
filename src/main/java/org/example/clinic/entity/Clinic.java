package org.example.clinic.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "clinics")
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "about")
    private String about;

    @Column(name = "certification")
    private String certification;

    @Column(name = "address")
    private String address;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "weblink")
    private String weblink;

    @Column(name = "since")
    private Instant since;

    @ColumnDefault("0")
    @Column(name = "rating")
    private Float rating;

    @OneToMany(mappedBy = "clinic")
    private Set<ClinicReview> clinicReviews = new LinkedHashSet<>();

    @OneToMany(mappedBy = "clinic")
    private Set<ClinicRoom> clinicRooms = new LinkedHashSet<>();

    @OneToMany(mappedBy = "clinic")
    private Set<ClinicService> clinicServices = new LinkedHashSet<>();

    @OneToMany(mappedBy = "clinic")
    private Set<DoctorClinic> doctorClinics = new LinkedHashSet<>();

    @OneToMany(mappedBy = "clinic")
    private Set<WorkingHour> workingHours = new LinkedHashSet<>();

}