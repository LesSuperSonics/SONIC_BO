package com.capgemini.candidateorganizationsystem.controllers;

import com.capgemini.candidateorganizationsystem.dto.UserDto;
import com.capgemini.candidateorganizationsystem.entities.CandidateEntity;
import com.capgemini.candidateorganizationsystem.entities.UserEntity;
import com.capgemini.candidateorganizationsystem.exceptions.UserException;
import com.capgemini.candidateorganizationsystem.message.ErrorMessages;
import com.capgemini.candidateorganizationsystem.repositories.UserRepository;
import com.capgemini.candidateorganizationsystem.services.UserService;
import com.sipios.springsearch.anotation.SearchSpec;
import org.modelmapper.ModelMapper;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;



    @GetMapping("")
    public ResponseEntity<List<UserEntity>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable long id) {

        UserDto userDto = userService.getUserById(id);


        return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
    }



    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userRequest) throws Exception {

       if(userRequest.getFirstName().isEmpty()) throw new UserException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());


        ModelMapper modelMapper = new ModelMapper();
        //UserDto userDto = modelMapper.map(userRequest, UserDto.class);

        UserDto createUser = userService.createUser(userRequest);

        return new ResponseEntity<UserDto>(createUser, HttpStatus.CREATED);


    }













}
