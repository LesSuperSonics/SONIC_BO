package com.capgemini.candidateorganizationsystem.repository;

import com.capgemini.candidateorganizationsystem.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
