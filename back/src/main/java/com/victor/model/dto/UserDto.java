package com.victor.model.dto;

import com.victor.model.Role;
import com.victor.model.Sex;
import org.springframework.stereotype.Component;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;

@Component
public class UserDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String email;
    @Enumerated(value = EnumType.STRING)
    private Sex sex;
    private URL urlPhoto;
    private LocalDate dateOfBirth;
    @Enumerated(value = EnumType.STRING)
    private Role role;

    public UserDto() {
    }

    public UserDto(Long id, String firstName, String lastName, String login, String password, String email, Sex sex, URL urlPhoto, LocalDate dateOfBirth, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.sex = sex;
        this.urlPhoto = urlPhoto;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public URL getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(URL urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return id.equals(userDto.id) &&
                firstName.equals(userDto.firstName) &&
                lastName.equals(userDto.lastName) &&
                login.equals(userDto.login) &&
                password.equals(userDto.password) &&
                email.equals(userDto.email) &&
                sex == userDto.sex &&
                urlPhoto.equals(userDto.urlPhoto) &&
                dateOfBirth.equals(userDto.dateOfBirth) &&
                role == userDto.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, login, password, email, sex, urlPhoto, dateOfBirth, role);
    }
}