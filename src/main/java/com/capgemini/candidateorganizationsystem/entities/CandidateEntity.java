package com.capgemini.candidateorganizationsystem.entities;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "candidates")
@EntityListeners(AuditingEntityListener.class)
public class CandidateEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private long id;

  @Column(name = "firstName")
  private String firstName;

  @Column(name = "lastName")
  private String lastName;

  @Column(name = "phoneNumber")
  private String phoneNumber;

  @Column(name = "email",nullable = false, length = 120, unique = true)
  private String email;

  @Column(name = "address")
  private String address;

  @Column(name = "expDuration")
  private int expDuration;

  @Column(name = "profile")
  private String profile;

  @CreatedDate
  @Column(name = "createdDate")
  private Date createdDate;

  @Column(length = 32, columnDefinition = "varchar(32) default 'CURRENT'")
  @Enumerated(value = EnumType.STRING)
  private CandidateStatus status = CandidateStatus.CURRENT;

  @ManyToOne
  @JoinColumn(name = "users_id")
  private UserEntity user;

  public CandidateEntity() {

  }

  public CandidateEntity(String firstName, String lastName, String phoneNumber, String email, String address, int expDuration, String profile) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.address = address;
    this.expDuration = expDuration;
    this.profile = profile;
  }

  public CandidateEntity(long id, String firstName, String lastName, String phoneNumber, String email, String address, int expDuration, String profile, Date createdDate, CandidateStatus status, UserEntity user) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.address = address;
    this.expDuration = expDuration;
    this.profile = profile;
    this.createdDate = createdDate;
    this.status = status;
    this.user = user;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public int getExpDuration() {
    return expDuration;
  }

  public void setExpDuration(int expDuration) {
    this.expDuration = expDuration;
  }

  public String getProfile() {
    return profile;
  }

  public void setProfile(String profile) {
    this.profile = profile;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public CandidateStatus getStatus() {
    return status;
  }

  public void setStatus(CandidateStatus status) {
    this.status = status;
  }

  public UserEntity getUser() {
    return user;
  }

  public void setUser(UserEntity user) {
    this.user = user;
  }
}
