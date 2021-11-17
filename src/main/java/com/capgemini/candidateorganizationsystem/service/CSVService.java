package com.capgemini.candidateorganizationsystem.service;
import com.capgemini.candidateorganizationsystem.helper.CSVHelper;
import com.capgemini.candidateorganizationsystem.model.Candidate;
import com.capgemini.candidateorganizationsystem.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class CSVService {
  @Autowired
  CandidateRepository repository;

  public void save(MultipartFile file) {
    try {
      List<Candidate> candidates = CSVHelper.csvToCandidates(file.getInputStream());
      repository.saveAll(candidates);
    } catch (IOException e) {
      throw new RuntimeException("fail to store csv data: " + e.getMessage());
    }
  }

  public ByteArrayInputStream load() {
    List<Candidate> candidates = repository.findAll();

    ByteArrayInputStream in = CSVHelper.CandidatesToCSV(candidates);
    return in;
  }

  public List<Candidate> getAllCandidates() {
    return repository.findAll();
  }
}
