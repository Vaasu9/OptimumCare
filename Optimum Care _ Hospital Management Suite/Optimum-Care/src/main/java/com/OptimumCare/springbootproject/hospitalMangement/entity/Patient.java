package com.OptimumCare.springbootproject.hospitalMangement.entity;

import com.OptimumCare.springbootproject.hospitalMangement.entity.type.BloodGroupType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate dateOfBirth;

    private String email;

    private String gender;

    @Enumerated(EnumType.STRING)
    private BloodGroupType bloodGroup;

    private LocalDateTime createdAt;

    @OneToOne(cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Insurance insurance;

    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Appointment> appointments;

}
