package com.gulbagomedovich.brs.service;

import com.gulbagomedovich.brs.dto.UserDto;
import com.gulbagomedovich.brs.model.Role;
import com.gulbagomedovich.brs.model.User;
import com.gulbagomedovich.brs.model.UserRoles;
import com.gulbagomedovich.brs.repository.RoleRepository;
import com.gulbagomedovich.brs.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

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
                    .setEmail(userDto.getEmail())
                    .setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

            return UserDto.toUserDto(userRepository.save(user));
        } else {
            return null;
        }
    }

}
