package com.gulbagomedovich.brs.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.gulbagomedovich.brs.model.Role;
import com.gulbagomedovich.brs.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;
    private String password;

    private boolean admin;

    private Set<RoleDto> roles;

    public String getFullName() {
        return firstName != null ? firstName + " " + lastName : "";
    }

    public static UserDto toUserDto(User user) {
        Set<RoleDto> tempRoles = new HashSet<>();

        for (Role role : user.getRoles()) {
            tempRoles.add(RoleDto.toRoleDto(role));
        }

        return new UserDto()
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setMobileNumber(user.getMobileNumber())
                .setEmail(user.getEmail())
                .setRoles(tempRoles);
    }

}
