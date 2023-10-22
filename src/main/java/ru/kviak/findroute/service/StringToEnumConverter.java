package ru.kviak.findroute.service;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.kviak.findroute.model.CalculationType;

@Component
public class StringToEnumConverter implements Converter<String, CalculationType> {
    @Override
    public CalculationType convert(String source) {
        return EnumUtils.getEnum(CalculationType.class, source.toUpperCase().replace(' ', '_'));
    }
}
