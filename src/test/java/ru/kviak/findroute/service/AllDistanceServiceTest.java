package ru.kviak.findroute.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.kviak.findroute.model.CalculationType;
import ru.kviak.findroute.model.CityDistanceResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

public class AllDistanceServiceTest {

    @Mock
    private CrowflightDistanceService crowflightDistanceService;
    @Mock
    private MatrixDistanceService matrixDistanceService;

    private AllDistanceService allDistanceService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        allDistanceService = new AllDistanceService(crowflightDistanceService, matrixDistanceService);
    }

    @Test
    public void calculate_ShouldCallCrowflightAndMatrixServices() {
        List<Long> fromCityIds = new ArrayList<>();
        List<Long> toCityIds = new ArrayList<>();
        List<CityDistanceResponse> crowflightResponse = new ArrayList<>();
        List<CityDistanceResponse> matrixResponse = new ArrayList<>();

        // Установить ожидаемый результат для crowflightDistanceService
        when(crowflightDistanceService.calculate(fromCityIds, toCityIds)).thenReturn(crowflightResponse);
        // Установить ожидаемый результат для matrixDistanceService
        when(matrixDistanceService.calculate(fromCityIds, toCityIds)).thenReturn(matrixResponse);

        // Вызвать метод calculate
        List<CityDistanceResponse> response = allDistanceService.calculate(fromCityIds, toCityIds);

        // Проверить, что методы calculate для crowflightDistanceService и matrixDistanceService были вызваны
        verify(crowflightDistanceService).calculate(fromCityIds, toCityIds);
        verify(matrixDistanceService).calculate(fromCityIds, toCityIds);
    }

    @Test
    public void getCalculationType_ShouldReturnAll() {
        CalculationType expectedType = CalculationType.ALL;

        // Вызвать метод getCalculationType
        CalculationType actualType = allDistanceService.getCalculationType();

        // Проверить, что возвращаемый тип совпадает с ожидаемым
        assertEquals(expectedType, actualType);
    }
}
