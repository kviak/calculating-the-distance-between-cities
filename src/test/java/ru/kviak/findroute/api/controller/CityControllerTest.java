package ru.kviak.findroute.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.kviak.findroute.model.CityResponse;
import ru.kviak.findroute.service.CityService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

public class CityControllerTest {

    @Mock
    private CityService cityService;
    private CityController cityController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cityController = new CityController(cityService);
    }

    @Test
    void getShouldReturnCitiesList(){
        List<CityResponse> list = new ArrayList<>();

        when(cityService.getAllCites()).thenReturn(list);

        assertThat(list).isEqualTo(cityController.get());
    }
}
