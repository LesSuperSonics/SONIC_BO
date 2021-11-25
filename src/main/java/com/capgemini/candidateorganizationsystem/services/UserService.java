package com.capgemini.candidateorganizationsystem.services;

import com.capgemini.candidateorganizationsystem.dto.UserDto;

import org.springframework.security.core.userdetails.UserDetailsService;



public interface UserService extends UserDetailsService {

    UserDto getUser(String email);

    UserDto getUserById(long id);

    UserDto createUser(UserDto user);



}
