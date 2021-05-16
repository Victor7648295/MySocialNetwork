package com.victor.converter;

public interface Converter<S, T> {

    Class<S> getSourceClass();

    Class<T> getTargetClass();

    T convert(S source);
}
