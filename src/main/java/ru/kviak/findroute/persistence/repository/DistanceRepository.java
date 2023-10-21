package ru.kviak.findroute.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kviak.findroute.persistence.entity.DistanceEntity;

import java.util.List;
import java.util.Optional;

public interface DistanceRepository extends JpaRepository<DistanceEntity, Long> {

    Optional<List<DistanceEntity>> findDistanceByFromCity_Name(String cityName);
}
