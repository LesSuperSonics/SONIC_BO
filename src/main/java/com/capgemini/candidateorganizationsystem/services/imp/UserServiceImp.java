package com.capgemini.candidateorganizationsystem.services.imp;


import com.capgemini.candidateorganizationsystem.dto.UserDto;
import com.capgemini.candidateorganizationsystem.entities.UserEntity;
import com.capgemini.candidateorganizationsystem.repositories.UserRepository;
import com.capgemini.candidateorganizationsystem.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDto getUser(String email) {

        UserEntity userEntity = userRepository.findByEmail(email);

        if(userEntity == null) throw new UsernameNotFoundException(email);

        UserDto userDto = new UserDto();

        BeanUtils.copyProperties(userEntity, userDto);

        return userDto;
    }

    @Override
    public List<UserEntity> getUsers() {

        List<UserEntity>  users = userRepository.findAll();

        return users;
    }

    @Override
    public UserDto getUserById(long id) {

        UserEntity userEntity = userRepository.findById(id);

        if(userEntity == null) throw new UsernameNotFoundException("user not found"+id+"");

        UserDto userDto = new UserDto();

        BeanUtils.copyProperties(userEntity, userDto);

        return userDto;
    }


    @Override
    public UserDto createUser(UserDto user) {

        UserEntity checkUser = userRepository.findByEmail(user.getEmail());

        if(checkUser != null) throw new RuntimeException("User Alrady Exists !");

        ModelMapper modelMapper = new ModelMapper();

        UserEntity userEntity = modelMapper.map(user, UserEntity.class);

        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userEntity.setEmail(user.getEmail());

        userEntity.setFirstName(user.getFirstName());

        userEntity.setLastName(user.getLastName());


        UserEntity newUser = userRepository.save(userEntity);

        UserDto userDto =  modelMapper.map(newUser, UserDto.class);

        return userDto;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByEmail(email);

        if(userEntity == null) throw new UsernameNotFoundException(email);

        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }


}
