package ru.kviak.findroute.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kviak.findroute.persistence.entity.CityEntity;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<CityEntity, Long> {

    Optional<CityEntity> findByName(String name);

    List<CityEntity> findAllByIdIn(List<Long> ids);
}
