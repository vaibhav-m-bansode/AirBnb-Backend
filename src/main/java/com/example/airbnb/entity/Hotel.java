package com.example.airbnb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String City;

    @SuppressWarnings("JpaAttributeTypeInspection")
    @Column(columnDefinition = "TEXT[]")
    private String[] photos;

    @SuppressWarnings("JpaAttributeTypeInspection")
    @Column(columnDefinition = "TEXT[]")
    private String[] amenities;


    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Embedded
    private HotelContactInfo contactInfo;

    private Boolean active;

    @OneToMany(mappedBy = "hotel" , fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Room>  rooms;
}
