package com.victor.converter;

import com.victor.exception.TypeConversionException;
import com.victor.model.User;
import com.victor.model.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoToUserConverter implements Converter <UserDto, User> {
    @Override
    public Class<UserDto> getSourceClass() {
        return UserDto.class;
    }

    @Override
    public Class<User> getTargetClass() {
        return User.class;
    }

    @Override
    public User convert(UserDto source) {
        return new User(source.getId(),
                        source.getFirstName(),
                        source.getLastName(),
                        source.getLogin(),
                        source.getPassword(),
                        source.getEmail(),
                        source.getSex(),
                        source.getUrlPhoto(),
                        source.getDateOfBirth(),
                        source.getRole());
    }

    @Override
    public List<User> convertList(List<UserDto> sourceList) {
        assertNotNull(sourceList);
        return sourceList
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    private void assertNotNull(List<?> list) {
        if (list == null) {
            throw new TypeConversionException("Fail to convert list of values: source list can not be null");
        }
    }
}
