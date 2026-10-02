package com.example.waqar.Converters;

import com.example.waqar.Entities.ChronicDiseases;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Converter
public class ChronicDiseaseConverter implements AttributeConverter<Set<ChronicDiseases>, String> {

    @Override
    public String convertToDatabaseColumn(Set<ChronicDiseases> diseases) {
        if (diseases == null || diseases.isEmpty()) {
            return ChronicDiseases.NONE.getDbValue(); // "none"
        }
        return diseases.stream()
                .map(ChronicDiseases::getDbValue)
                .collect(Collectors.joining(","));
    }

    @Override
    public Set<ChronicDiseases> convertToEntityAttribute(String dbValue) {
        if (dbValue == null || dbValue.isBlank()) {
            return Collections.singleton(ChronicDiseases.NONE);
        }
        return Arrays.stream(dbValue.split(","))
                .map(ChronicDiseases::fromDbValue)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
