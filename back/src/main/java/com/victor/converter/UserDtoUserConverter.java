package com.victor.converter;

import com.victor.model.User;
import com.victor.model.dto.UserDto;

public class UserDtoUserConverter implements Converter <UserDto, User> {
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
}
