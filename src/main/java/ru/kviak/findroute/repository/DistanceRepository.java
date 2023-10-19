package ru.kviak.findroute.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kviak.findroute.model.City;
import ru.kviak.findroute.model.Distance;

import java.util.List;
import java.util.Optional;

public interface DistanceRepository extends JpaRepository<Distance, Long> {

    Optional<List<Distance>> findDistanceByFromCity_Name(String cityName);
}
