package com.capgemini.candidateorganizationsystem.repositories;

import com.capgemini.candidateorganizationsystem.entities.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CandidateRepository extends JpaRepository<CandidateEntity, Long>, JpaSpecificationExecutor<CandidateEntity> {
}
