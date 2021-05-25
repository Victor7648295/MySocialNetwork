package com.victor.converter;

import com.victor.model.User;
import com.victor.model.dto.UserDto;

public class UserToUserDtoConverter implements Converter<User, UserDto> {
    @Override
    public Class<User> getSourceClass() {
        return User.class;
    }

    @Override
    public Class<UserDto> getTargetClass() {
        return UserDto.class;
    }

    @Override
    public UserDto convert(User source) {
        return new UserDto(source.getId(),
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
