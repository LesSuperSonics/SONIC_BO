package com.capgemini.candidateorganizationsystem.services;

import com.capgemini.candidateorganizationsystem.dto.UserDto;

import com.capgemini.candidateorganizationsystem.entities.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService extends UserDetailsService {

    UserDto getUser(String email);

    UserDto getUserById(long id);

    UserDto createUser(UserDto user);

    List<UserEntity> getUsers();

}
