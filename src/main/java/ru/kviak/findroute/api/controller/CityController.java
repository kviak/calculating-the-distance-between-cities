package ru.kviak.findroute.api.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kviak.findroute.model.CityResponse;
import ru.kviak.findroute.api.view.View;
import ru.kviak.findroute.service.CityService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/city")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping
    @JsonView(View.CityView.class)
    public List<CityResponse> get() {
        return cityService.getAllCites();
    }
}
