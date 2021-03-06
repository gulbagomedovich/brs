package com.gulbagomedovich.brs.service;

import com.gulbagomedovich.brs.dto.UserDto;
import com.gulbagomedovich.brs.model.Role;
import com.gulbagomedovich.brs.model.User;
import com.gulbagomedovich.brs.model.UserRoles;
import com.gulbagomedovich.brs.repository.RoleRepository;
import com.gulbagomedovich.brs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDto signup(UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail());
        Role role;

        if (user == null) {
            if (userDto.isAdmin()) {
                role = roleRepository.findByName(UserRoles.ADMIN.name());
            } else {
                role = roleRepository.findByName((UserRoles.PASSENGER.name()));
            }

            user = new User()
                    .setFirstName(userDto.getFirstName())
                    .setLastName(userDto.getLastName())
                    .setMobileNumber(userDto.getMobileNumber())
                    .setEmail(userDto.getEmail())
                    .setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()))
                    .setRoles(Collections.singleton(role));

            return UserDto.toUserDto(userRepository.save(user));
        } else {
            return null;
        }
    }

    public UserDto updateProfile(UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail());

        if (user != null) {
            user
                    .setFirstName(userDto.getFirstName())
                    .setLastName(userDto.getLastName())
                    .setMobileNumber(userDto.getMobileNumber())
                    .setEmail(userDto.getEmail());

            return UserDto.toUserDto(userRepository.save(user));
        } else {
            return null;
        }
    }

    public UserDto changePassword(UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail());

        if (user != null) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

            return UserDto.toUserDto(userRepository.save(user));
        } else {
            return null;
        }
    }

    public UserDto findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);

        if (user != null) {
            return UserDto.toUserDto(user);
        } else {
            return null;
        }
    }

}
