package com.victor.model.dto;

import com.victor.model.Role;
import com.victor.model.Sex;
import com.victor.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.net.URL;
import java.time.LocalDate;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String email;
    private Sex sex;
    private URL urlPhoto;
    private LocalDate dateOfBirth;
    private Role role;

    public UserDto(Long id, String firstName, String lastName, String login, String password, String email, LocalDate dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }
}
