package org.example.clinic.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "surname", length = 100)
    private String surname;

    @Lob
    @Column(name = "bio")
    private String bio;

    @Lob
    @Column(name = "certificates")
    private String certificates;

    @ColumnDefault("0")
    @Column(name = "rating")
    private Float rating;

    @OneToMany(mappedBy = "doctor")
    private Set<DoctorClinic> doctorClinics = new LinkedHashSet<>();

    @OneToMany(mappedBy = "doctor")
    private Set<DoctorReview> doctorReviews = new LinkedHashSet<>();

    @OneToMany(mappedBy = "doctor")
    private Set<DoctorWorkingHour> doctorWorkingHours = new LinkedHashSet<>();

}