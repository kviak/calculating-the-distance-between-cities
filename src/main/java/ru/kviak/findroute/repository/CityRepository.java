package ru.kviak.findroute.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kviak.findroute.model.City;

public interface CityRepository extends JpaRepository<City, Long> {
}
