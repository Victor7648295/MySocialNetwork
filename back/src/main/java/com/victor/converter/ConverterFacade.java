package com.victor.converter;

import com.victor.exception.TypeConversionException;

import java.util.List;

import static java.util.stream.Collectors.toList;

public interface ConverterFacade {

    <S, T> T convert(S source , Class<T> targetClz);

    default <S, T> List<T> convertList(List<S> sourceList, Class<T> targetClz) {
        assertNotNull(sourceList, targetClz);

        return sourceList
                .stream()
                .map(s -> convert(s, targetClz))
                .collect(toList());
    }

    private void assertNotNull(List<?> list, Class<?> targetClz) {
        if (list == null) {
            throw new TypeConversionException("Fail to convert list of values: source list can not be null");
        }
        if (targetClz == null) {
            throw new TypeConversionException("Fail to convert list of values: target class can not be null");
        }
    }
}
