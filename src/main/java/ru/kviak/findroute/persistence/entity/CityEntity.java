package ru.kviak.findroute.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "city")
public class CityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "longitude", nullable = false, unique = true)
    private double longitude;

    @Column(name = "latitude", nullable = false, unique = true)
    private double latitude;
}
