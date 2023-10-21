package ru.kviak.findroute.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.kviak.findroute.model.CalculationType;
import ru.kviak.findroute.model.CityDistanceResponse;
import ru.kviak.findroute.service.AllDistanceService;
import ru.kviak.findroute.service.CrowflightDistanceService;
import ru.kviak.findroute.service.DistanceServiceProvider;
import ru.kviak.findroute.service.MatrixDistanceService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

public class DistanceControllerTest {

    private DistanceController distanceController;
    @Mock
    private DistanceServiceProvider distanceServiceProvider;
    @Mock
    private CrowflightDistanceService crowflightDistanceService;
    @Mock
    private MatrixDistanceService matrixDistanceService;
    @Mock
    private AllDistanceService allDistanceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        distanceController = new DistanceController(distanceServiceProvider);
    }

    @Test
    void getShouldReturnCityDistanceResponseListCalculatedByCrowFlight(){
        List<CityDistanceResponse> expected = new ArrayList<>();
        List<Long> fromCityId = new ArrayList<>();
        List<Long> toCityId = new ArrayList<>();
        CalculationType calculationType = CalculationType.CROWFLIGHT;

        when(distanceServiceProvider.getDistanceService(calculationType)).thenReturn(crowflightDistanceService);
        when(crowflightDistanceService.calculate(fromCityId, toCityId)).thenReturn(expected);
        List<CityDistanceResponse> returned = distanceController.calculateDistance(fromCityId, toCityId, calculationType);

        assertThat(expected).isEqualTo(returned);
    }

    @Test
    void getShouldReturnCityDistanceResponseListCalculatedByDistanceMatrix(){
        List<CityDistanceResponse> expected = new ArrayList<>();
        List<Long> fromCityId = new ArrayList<>();
        List<Long> toCityId = new ArrayList<>();
        CalculationType calculationType = CalculationType.DISTANCE_MATRIX;

        when(distanceServiceProvider.getDistanceService(calculationType)).thenReturn(matrixDistanceService);
        when(matrixDistanceService.calculate(fromCityId, toCityId)).thenReturn(expected);
        List<CityDistanceResponse> returned = distanceController.calculateDistance(fromCityId, toCityId, calculationType);

        assertThat(expected).isEqualTo(returned);
    }

    @Test
    void getShouldReturnCityDistanceResponseListCalculatedAll(){
        List<CityDistanceResponse> expected = new ArrayList<>();
        List<Long> fromCityId = new ArrayList<>();
        List<Long> toCityId = new ArrayList<>();
        CalculationType calculationType = CalculationType.ALL;

        when(distanceServiceProvider.getDistanceService(calculationType)).thenReturn(allDistanceService);
        when(allDistanceService.calculate(fromCityId, toCityId)).thenReturn(expected);
        List<CityDistanceResponse> returned = distanceController.calculateDistance(fromCityId, toCityId, calculationType);

        assertThat(expected).isEqualTo(returned);
    }
}
