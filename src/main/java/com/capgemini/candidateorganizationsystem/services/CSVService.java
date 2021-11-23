package com.capgemini.candidateorganizationsystem.services;
import com.capgemini.candidateorganizationsystem.entities.CandidateEntity;
import com.capgemini.candidateorganizationsystem.helper.CSVHelper;
import com.capgemini.candidateorganizationsystem.repositories.CandidateRepository;
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
      List<CandidateEntity> candidates = CSVHelper.csvToCandidates(file.getInputStream());
      repository.saveAll(candidates);
    } catch (IOException e) {
      throw new RuntimeException("fail to store csv data: " + e.getMessage());
    }
  }

  public ByteArrayInputStream load() {
    List<CandidateEntity> candidates = repository.findAll();

    ByteArrayInputStream in = CSVHelper.CandidatesToCSV(candidates);
    return in;
  }

  public List<CandidateEntity> getAllCandidates() {
    return repository.findAll();
  }
}
