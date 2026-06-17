package com.example.airbnb.entity;


import com.example.airbnb.entity.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> role;

    @OneToMany(mappedBy = "user")
    private Set<Booking>  bookings;
}
