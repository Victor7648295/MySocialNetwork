package com.victor.converter;

import com.victor.exception.TypeConversionException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class ConverterFacadeImpl implements ConverterFacade {


    private final List<Converter<?, ?>> converters;

    private final Map<ConversionDescriptor, Converter<?, ?>> converterRegistry = new HashMap<>();

    public ConverterFacadeImpl(List<Converter<?, ?>> converters) {
        this.converters = converters;
    }

    @PostConstruct
    protected void populateConverterRegistry() {
        for (Converter<?, ?> converter : converters) {
            ConversionDescriptor descriptor = new ConversionDescriptor(converter.getSourceClass(), converter.getTargetClass());
            Converter<?, ?> alreadyRegistered = converterRegistry.put(descriptor, converter);

            if (alreadyRegistered != null) {
                throw new TypeConversionException(
                        "Duplicate type converter found:[%s]->[%s], already registered:[%s], register candidate:[%s]",
                        descriptor.srcClz, descriptor.targetClz, alreadyRegistered.getClass().getSimpleName(),
                        converter.getClass().getSimpleName());
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <S, T> T convert(S source, Class<T> targetClz) {
        assertNotNull(source, targetClz);
        Converter<S, T> targetConverter = (Converter<S, T>) getConverterFor(source.getClass(), targetClz);
        return targetConverter.convert(source);
    }

    private Converter<?, ?> getConverterFor(Class<?> sourceClz, Class<?> targetClz) {
        Converter<?, ?> result = converterRegistry.get(new ConversionDescriptor(sourceClz, targetClz));

        assertConverterExists(sourceClz, targetClz, result);

        return result;
    }


    private void assertConverterExists(Class<?> sourceClz, Class<?> targetClz, Converter<?, ?> result) {
        if (result == null) {
            throw new TypeConversionException("Failed to find type converter for:[%s]->[%s] conversion",
                                              sourceClz, targetClz);
        }
    }

    private void assertNotNull(Object source, Class<?> targetClz) {
        if (source == null) {
            throw new TypeConversionException("Fail to convert value: source object can not be null");
        }
        if (targetClz == null) {
            throw new TypeConversionException("Fail to convert value: target class can not be null");
        }
    }

    private static class ConversionDescriptor {

        private final Class<?> srcClz;

        private final Class<?> targetClz;

        public ConversionDescriptor(Class<?> srcClz, Class<?> targetClz) {
            this.srcClz = srcClz;
            this.targetClz = targetClz;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            ConversionDescriptor that = (ConversionDescriptor) o;

            return Objects.equals(srcClz, that.srcClz)
                    && Objects.equals(targetClz, that.targetClz);
        }

        @Override
        public int hashCode() {
            return Objects.hash(srcClz, targetClz);
        }
    }
}
