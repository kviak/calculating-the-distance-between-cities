package ru.kviak.findroute.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kviak.findroute.model.City;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {

    Optional<City> findByName(String name);
}
