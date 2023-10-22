package ru.kviak.findroute.model.xml;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CitiesAndDistancesWrapperTest {

    @Test
    public void testCitiesAndDistancesWrapper() {
        // Создаем список городов
        List<CityXmlUpload> cities = new ArrayList<>();
        cities.add(new CityXmlUpload("Москва", 37.6175, 55.7558));
        cities.add(new CityXmlUpload("Санкт-Петербург", 30.3158, 59.9390));

        // Создаем список расстояний
        List<Distance> distances = new ArrayList<>();
        distances.add(new Distance("Москва", "Санкт-Петербург", 700.0));
        distances.add(new Distance("Москва", "Казань", 800.0));

        // Создаем объект CitiesAndDistancesWrapper
        CitiesAndDistancesWrapper wrapper = new CitiesAndDistancesWrapper(cities, distances);

        // Проверяем, что поля объекта корректно установлены
        Assertions.assertEquals(cities, wrapper.getCities());
        Assertions.assertEquals(distances, wrapper.getDistances());
    }
}
