package com.victor.exception;

import static java.lang.String.format;

public class TypeConversionException extends RuntimeException {

    public TypeConversionException(String message) {
        super(message);
    }

    public TypeConversionException(String message, Class<?> sourceClz, Class<?> targetClz) {
        super(format(message, sourceClz, targetClz));
    }

    public TypeConversionException(String message, Object... args) {
        super(format(message, args));
    }
}
