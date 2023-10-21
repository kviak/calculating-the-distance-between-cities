package ru.kviak.findroute.mapper;

import org.mapstruct.*;
import ru.kviak.findroute.model.CityResponse;
import ru.kviak.findroute.persistence.entity.CityEntity;

@Mapper(componentModel = "Spring")
public interface CityMapper {

    CityResponse mapTo(CityEntity cityEntity);


}
