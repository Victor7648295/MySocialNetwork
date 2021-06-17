package com.victor.converter;

import java.util.List;

public interface Converter<S, T> {

    Class<S> getSourceClass();

    Class<T> getTargetClass();

    T convert(S source);

    List<T> convertList(List<S> sourceList);
}