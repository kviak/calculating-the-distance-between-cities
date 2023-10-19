package ru.kviak.findroute.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kviak.findroute.dto.CityDto;
import ru.kviak.findroute.dto.DistanceDto;
import ru.kviak.findroute.service.CalculateDistanceService;
import ru.kviak.findroute.service.CityService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class Controller {
    private final CityService cityService;
    private final CalculateDistanceService calculateDistanceService;

    @GetMapping("/city")
    public ResponseEntity<List<CityDto>> handleCity(){
        return ResponseEntity.ok(cityService.getAllCites());
    }

    @GetMapping("/distance")
    public ResponseEntity<List<DistanceDto>> handleDistance(){
        return ResponseEntity.ok(calculateDistanceService.getAll());
    }

    @GetMapping("/calc")
    public ResponseEntity<List<?>> calculateDistance(@RequestBody List<List<CityDto>> list,
                                                               @RequestParam(defaultValue = "Crowflight", name = "CalculationType") String calculationType){

        return ResponseEntity.ok(calculateDistanceService.calculate(calculationType, list));
    }
}
