package com.capgemini.candidateorganizationsystem;

import com.capgemini.candidateorganizationsystem.entities.CandidateEntity;
import com.capgemini.candidateorganizationsystem.repositories.CandidateRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class CandidateOrganizationSystemApplicationTests {

	private CandidateRepository candidateRepository;

	@Test
	public void findAll(){
		candidateRepository = mock(CandidateRepository.class);
		List<CandidateEntity> candidates = new ArrayList<>();
		candidates.add(new CandidateEntity());
		candidates.add(new CandidateEntity());

		when(candidateRepository.findAll()).thenReturn(candidates);

		List<CandidateEntity> expectedList = candidateRepository.findAll();

		assertEquals(2, expectedList.size());
	}



	@Test
	public void contextLoads() {
	}

}



