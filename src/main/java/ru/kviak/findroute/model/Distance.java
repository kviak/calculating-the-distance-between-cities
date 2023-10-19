package ru.kviak.findroute.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "distance")
public class Distance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "distance_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "from_city_id")
    private City fromCity;

    @ManyToOne
    @JoinColumn(name = "to_city_id")
    private City toCity;

    @Column(name = "distance")
    private Double distance;
}
