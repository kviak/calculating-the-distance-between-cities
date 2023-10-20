package ru.kviak.findroute.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "longitude", nullable = false, unique = true)
    private double longitude;

    @Column(name = "latitude", nullable = false, unique = true)
    private double latitude;

//    @OneToMany
//    private List<Distance> distances;

//    @OneToMany(mappedBy = "toCity")
//    private List<Distance> distancesTo;
}
