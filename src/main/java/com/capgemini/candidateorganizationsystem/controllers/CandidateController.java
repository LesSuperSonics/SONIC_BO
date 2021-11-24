package com.capgemini.candidateorganizationsystem.controllers;

import com.capgemini.candidateorganizationsystem.entities.CandidateEntity;
import com.capgemini.candidateorganizationsystem.entities.CandidateStatus;
import com.capgemini.candidateorganizationsystem.repositories.CandidateRepository;
import com.sipios.springsearch.anotation.SearchSpec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("/api/candidates")
public class CandidateController {
    private final CandidateRepository candidateRepository;

    public CandidateController(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    // The search feature - All search candidates requests are implemented by this route ^^
    @GetMapping("")
    public ResponseEntity<List<CandidateEntity>> searchForCandidates(@SearchSpec Specification<CandidateEntity> specs) {
        return new ResponseEntity<>(candidateRepository.findAll(Specification.where(specs)), HttpStatus.OK);
    }

    // To find a list of candidates by status enum ACCEPTED,CURRENT,REJECTED
    @GetMapping("/findByStatus/{status}")
    public ResponseEntity<List<CandidateEntity>> searchForCandidatesStatus(@PathVariable("status") CandidateStatus status) {
        return new ResponseEntity<>(candidateRepository.findByStatus(status), HttpStatus.OK);
    }

    @PostMapping("")
    @ResponseStatus(code = HttpStatus.OK, reason = "OK")
    CandidateEntity newCandidate(@RequestBody CandidateEntity newCandidate) {
        return candidateRepository.save(newCandidate);
    }

    // To pass the status of a candidate to ACCEPTED || REJECTED
    @PutMapping("candidate/changeStatus/{id}")
    @ResponseStatus(code = HttpStatus.OK, reason = "OK")
    CandidateEntity AcceptCandidate(@RequestBody CandidateEntity newCandidateEmployee, @PathVariable Long id) {

        return candidateRepository.findById(id)
                .map(CandidateEntity -> {
                    CandidateEntity.setStatus(newCandidateEmployee.getStatus());
                    return candidateRepository.save(CandidateEntity);
                })
                .orElseGet(() -> {
                    newCandidateEmployee.setId(id);
                    return candidateRepository.save(newCandidateEmployee);
                });
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK, reason = "OK")
    void deleteCandidate(@PathVariable Long id) {
        candidateRepository.deleteById(id);
    }
}
