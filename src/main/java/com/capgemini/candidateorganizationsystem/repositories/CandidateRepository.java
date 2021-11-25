package com.capgemini.candidateorganizationsystem.repositories;

import com.capgemini.candidateorganizationsystem.entities.CandidateEntity;
import com.capgemini.candidateorganizationsystem.entities.CandidateStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CandidateRepository extends JpaRepository<CandidateEntity, Long>, JpaSpecificationExecutor<CandidateEntity> {
    public List<CandidateEntity> findByStatus(CandidateStatus status);
}
