package com.capgemini.candidateorganizationsystem.repositories;

import com.capgemini.candidateorganizationsystem.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
