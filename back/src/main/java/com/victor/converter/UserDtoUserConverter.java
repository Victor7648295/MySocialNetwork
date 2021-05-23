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
        return new User(source.id(),
                           source.firstName(),
                           source.lastName(),
                           source.login(),
                           source.password(),
                           source.email(),
                           source.sex(),
                           source.urlPhoto(),
                           source.dateOfBirth(),
                           source.role());
    }
}
