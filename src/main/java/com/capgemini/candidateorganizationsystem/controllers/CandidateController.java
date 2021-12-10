package com.capgemini.candidateorganizationsystem.controllers;

import com.capgemini.candidateorganizationsystem.entities.CandidateEntity;
import com.capgemini.candidateorganizationsystem.entities.CandidateStatus;
import com.capgemini.candidateorganizationsystem.repositories.CandidateRepository;
import com.sipios.springsearch.anotation.SearchSpec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // To find FullStack candidates order by received date
    @GetMapping("/findFullStacks")
    public ResponseEntity<List<CandidateEntity>> searchForCandidatesFullStack() {
        return new ResponseEntity<>(candidateRepository.findAllFullStackCandidatesNative(), HttpStatus.OK);
    }

    // To find Testing candidates order by received date
    @GetMapping("/findTestings")
    public ResponseEntity<List<CandidateEntity>> searchForCandidatesTesting() {
        return new ResponseEntity<>(candidateRepository.findAllTestingCandidatesNative(), HttpStatus.OK);
    }

    // To find SalesForce candidates order by received date
    @GetMapping("/findSalesForces")
    public ResponseEntity<List<CandidateEntity>> searchForCandidatesSalesForce() {
        return new ResponseEntity<>(candidateRepository.findAllSalesForceCandidatesNative(), HttpStatus.OK);
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

    // Dashboard Needed routes

    // Profiles
    @GetMapping("/fullstackCount")
    public ResponseEntity<Object> FullStackCount() {
        Map<String, Object> map = new HashMap<String, Object>();
        Long FullStackCount = candidateRepository.CalculateFullStackCandidatesNative();
        map.put("Total_FullStack", FullStackCount);
        return new ResponseEntity<Object>(map,HttpStatus.OK);
    }
    @GetMapping("/testingCount")
    public ResponseEntity<Object> TestingCount() {
        Map<String, Object> map = new HashMap<String, Object>();
        Long TestingCount = candidateRepository.CalculateTestingCandidatesNative();
        map.put("Total_Testing", TestingCount);
        return new ResponseEntity<Object>(map,HttpStatus.OK);
    }
    @GetMapping("/salesforceCount")
    public ResponseEntity<Object> SalesForceCount() {
        Map<String, Object> map = new HashMap<String, Object>();
        Long SalesForceCount = candidateRepository.CalculateSalesForceCandidatesNative();
        map.put("Total_SalesForce", SalesForceCount);
        return new ResponseEntity<Object>(map,HttpStatus.OK);
    }

    // Status
    @GetMapping("/CurrentStatusCount")
    public ResponseEntity<Object> CurrentCount() {
        Map<String, Object> map = new HashMap<String, Object>();
        Long CurrentCount = candidateRepository.CalculateCurrentCandidatesNative();
        map.put("Total_Current", CurrentCount);
        return new ResponseEntity<Object>(map,HttpStatus.OK);
    }

    @GetMapping("/AcceptedStatusCount")
    public ResponseEntity<Object> AcceptedCount() {
        Map<String, Object> map = new HashMap<String, Object>();
        Long AcceptedCount = candidateRepository.CalculateAcceptedCandidatesNative();
        map.put("Total_Accepted", AcceptedCount);
        return new ResponseEntity<Object>(map,HttpStatus.OK);
    }

    @GetMapping("/RejectedStatusCount")
    public ResponseEntity<Object> RejectedCount() {
        Map<String, Object> map = new HashMap<String, Object>();
        Long RejectedCount = candidateRepository.CalculateRejectedCandidatesNative();
        map.put("Total_Rejected", RejectedCount);
        return new ResponseEntity<Object>(map,HttpStatus.OK);
    }

    // Charts
    // Bar Chart candidates count of each profile by Years
    // Count FullStack by Years
    @GetMapping("/FullStackByYearsBarChart")
    public ResponseEntity<List<Object[]>> FullStackByYearsBarChart() {
        Map<String, List<Object[]>> map = new HashMap<>();
         List<Object[]> barChart = candidateRepository.FullStackCountByYearsBarChart();
        map.put("Data", barChart);
        return new ResponseEntity<List<Object[]>>(barChart, HttpStatus.OK);
    }
    // Count Testing by Years
    @GetMapping("/TestingByYearsBarChart")
    public ResponseEntity<List<Object[]>> TestingByYearsBarChart() {
        Map<String, List<Object[]>> map = new HashMap<>();
        List<Object[]> barChart = candidateRepository.TestingCountByYearsBarChart();
        map.put("Data", barChart);
        return new ResponseEntity<List<Object[]>>(barChart, HttpStatus.OK);
    }
    // Count SalesForce by Years
    @GetMapping("/SalesForceByYearsBarChart")
    public ResponseEntity<List<Object[]>> SalesForceByYearsBarChart() {
        Map<String, List<Object[]>> map = new HashMap<>();
        List<Object[]> barChart = candidateRepository.SalesForceCountByYearsBarChart();
        map.put("Data", barChart);
        return new ResponseEntity<List<Object[]>>(barChart, HttpStatus.OK);
    }
    // TimeLine candidates Chart
    // Count Candidates by Years
    @GetMapping("/CandidatesByYearsTimeLineChart")
    public ResponseEntity<List<Object[]>> CandidatesCountByYearsTimeLineChart() {
        Map<String, List<Object[]>> map = new HashMap<>();
        List<Object[]> TimeLineChart = candidateRepository.CandidatesCountByYearsTimeLineChart();
        map.put("Data", TimeLineChart);
        return new ResponseEntity<List<Object[]>>(TimeLineChart, HttpStatus.OK);
    }
}
