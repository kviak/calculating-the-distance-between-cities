package ru.kviak.findroute.service;

import org.springframework.stereotype.Service;
import ru.kviak.findroute.model.CalculationType;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DistanceServiceProvider {

    private final Map<CalculationType, DistanceService> distanceServices;

    public DistanceServiceProvider(final List<DistanceService> distanceServices) {
        this.distanceServices = distanceServices.stream()
                .collect(Collectors.toMap(DistanceService::getCalculationType, Function.identity()));
    }

    public DistanceService getDistanceService(CalculationType calculationType) {
        return distanceServices.get(calculationType);
    }
}
