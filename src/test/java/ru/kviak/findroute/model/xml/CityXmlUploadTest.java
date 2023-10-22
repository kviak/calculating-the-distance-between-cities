package ru.kviak.findroute.model.xml;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CityXmlUploadTest {
    @Test
    public void testCityXmlUpload() {
        // Создаем объект CityXmlUpload
        CityXmlUpload city = new CityXmlUpload("Москва", 37.6175, 55.7558);

        // Проверяем, что поля объекта корректно установлены
        Assertions.assertEquals("Москва", city.getName());
        Assertions.assertEquals(37.6175, city.getLongitude());
        Assertions.assertEquals(55.7558, city.getLatitude());
    }
}
