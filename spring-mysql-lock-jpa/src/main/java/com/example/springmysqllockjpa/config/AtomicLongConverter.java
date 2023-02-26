package com.example.springmysqllockjpa.config;

import javax.persistence.AttributeConverter;
import java.util.concurrent.atomic.AtomicLong;


public class AtomicLongConverter implements AttributeConverter<AtomicLong, Long> {
    @Override
    public Long convertToDatabaseColumn(AtomicLong attribute) {
        return attribute.get();
    }

    @Override
    public AtomicLong convertToEntityAttribute(Long dbData) {
        return new AtomicLong(dbData);
    }
}
