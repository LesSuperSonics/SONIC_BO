package com.capgemini.candidateorganizationsystem;

import com.capgemini.candidateorganizationsystem.entities.CandidateEntity;
import com.capgemini.candidateorganizationsystem.entities.CandidateStatus;
import com.capgemini.candidateorganizationsystem.repositories.CandidateRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CandidateOrganizationSystemApplicationTests {

	private CandidateRepository candidateRepository;
	Date mockReceivedDate = new Date();

	@Test
	public void findAll(){
		candidateRepository = mock(CandidateRepository.class);
		List<CandidateEntity> candidates = new ArrayList<>();
		candidates.add(new CandidateEntity("CIN1","PASSID1","Younes","OUFRID","06876376274","oufridyounes@gmail.com","Rabat",2,"FullStack",mockReceivedDate));
		candidates.add(new CandidateEntity("CIN2","PASSID2","SalahEddine","HALKHOUMS","06876345274","halkhoumssalaheddine@gmail.com","Rabat",2,"FullStack",mockReceivedDate));
		when(candidateRepository.findAll()).thenReturn(candidates);
		List<CandidateEntity> expectedList = candidateRepository.findAll();
		assertEquals(2, expectedList.size());
		assertEquals("CIN1", expectedList.get(0).getCin());
	}
	@Test
	public void findByStatus() {
		candidateRepository = mock(CandidateRepository.class);
		List<CandidateEntity> candidates = new ArrayList<>();
		candidates.add(new CandidateEntity("CIN1", "PASSID1", "Younes", "OUFRID", "06876376274", "oufridyounes@gmail.com", "Rabat", 2, "FullStack", mockReceivedDate));
		candidates.add(new CandidateEntity("CIN2", "PASSID2", "SalahEddine", "HALKHOUMS", "06876345274", "halkhoumssalaheddine@gmail.com", "Rabat", 2, "FullStack", mockReceivedDate));
		when(candidateRepository.findByStatus(CandidateStatus.CURRENT)).thenReturn(candidates);
		List<CandidateEntity> expectedList = candidateRepository.findByStatus(CandidateStatus.CURRENT);
		assertEquals(2, expectedList.size());
		assertEquals(CandidateStatus.CURRENT, expectedList.get(0).getStatus());
	}


	@Test
	public void findByCin() {
		candidateRepository = mock(CandidateRepository.class);
		List<CandidateEntity> candidates = new ArrayList<>();
		candidates.add(new CandidateEntity("CIN1", "PASSID1", "Younes", "OUFRID", "06876376274", "oufridyounes@gmail.com", "Rabat", 2, "FullStack", mockReceivedDate));
		candidates.add(new CandidateEntity("CIN2", "PASSID2", "SalahEddine", "HALKHOUMS", "06876345274", "halkhoumssalaheddine@gmail.com", "Rabat", 2, "FullStack", mockReceivedDate));
		when(candidateRepository.findByCin("CIN2")).thenReturn(candidates);
		List<CandidateEntity> expectedList = candidateRepository.findByCin("CIN2");
		assertEquals("CIN2", expectedList.get(1).getCin());
	}

	@Test
	public void findByPassportId() {
		candidateRepository = mock(CandidateRepository.class);
		List<CandidateEntity> candidates = new ArrayList<>();
		candidates.add(new CandidateEntity("CIN1", "PASSID1", "Younes", "OUFRID", "06876376274", "oufridyounes@gmail.com", "Rabat", 2, "FullStack", mockReceivedDate));
		candidates.add(new CandidateEntity("CIN2", "PASSID2", "SalahEddine", "HALKHOUMS", "06876345274", "halkhoumssalaheddine@gmail.com", "Rabat", 2, "FullStack", mockReceivedDate));
		when(candidateRepository.findByPassportId("PASSID2")).thenReturn(candidates);
		List<CandidateEntity> expectedList = candidateRepository.findByPassportId("PASSID2");
		assertEquals("PASSID2", expectedList.get(1).getPassportId());
	}

	@Test
	public void newCandidate() {
		candidateRepository = mock(CandidateRepository.class);
		CandidateEntity newCandidate = new CandidateEntity("CIN1", "PASSID1", "Younes", "OUFRID", "06876376274", "oufridyounes@gmail.com", "Rabat", 2, "FullStack", mockReceivedDate);
		when(candidateRepository.save(any(CandidateEntity.class))).thenReturn(newCandidate);
		assertEquals(0L, newCandidate.getId());
	}





	@Test
	public void contextLoads() {
	}

}



