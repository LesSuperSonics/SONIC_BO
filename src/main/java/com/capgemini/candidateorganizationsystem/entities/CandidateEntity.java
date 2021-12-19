package com.capgemini.candidateorganizationsystem.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "candidates")
@Builder
@EntityListeners(AuditingEntityListener.class)
public class CandidateEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private long id;

  @Column(name = "cin",nullable = false)
  private String cin;

  @Column(name = "passportId")
  private String passportId;

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

  @Column(name = "receivedDate")
  @JsonProperty("receivedDate")
  @JsonFormat(pattern="dd/MM/YYYY")
  private Date receivedDate;

  @CreatedDate
  @Column(name = "createdDate")
  private Date createdDate;

  @Column(name="status",length = 32, columnDefinition = "varchar(32) default 'CURRENT'")
  @Enumerated(value = EnumType.STRING)
  private CandidateStatus status = CandidateStatus.CURRENT;

  @ManyToOne
  @JoinColumn(name = "users_id")
  private UserEntity user;

  public CandidateEntity() {

  }

  public CandidateEntity(String cin, String passportId, String firstName, String lastName, String phoneNumber, String email, String address, int expDuration, String profile, Date receivedDate) {
    this.cin = cin;
    this.passportId = passportId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.address = address;
    this.expDuration = expDuration;
    this.profile = profile;
    this.receivedDate = receivedDate;
  }

  public CandidateEntity(long id, String cin, String passportId, String firstName, String lastName, String phoneNumber, String email, String address, int expDuration, String profile, Date receivedDate) {
    this.id = id;
    this.cin = cin;
    this.passportId = passportId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.address = address;
    this.expDuration = expDuration;
    this.profile = profile;
    this.receivedDate = receivedDate;
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

  public CandidateEntity(long id, String cin, String passportId, String firstName, String lastName, String phoneNumber, String email, String address, int expDuration, String profile, Date receivedDate, Date createdDate, CandidateStatus status, UserEntity user) {
    this.id = id;
    this.cin = cin;
    this.passportId = passportId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.address = address;
    this.expDuration = expDuration;
    this.profile = profile;
    this.receivedDate = receivedDate;
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

  public String getCin() {
    return cin;
  }

  public void setCin(String cin) {
    this.cin = cin;
  }

  public String getPassportId() {
    return passportId;
  }

  public void setPassportId(String passportId) {
    this.passportId = passportId;
  }

  public Date getReceivedDate() {
    return receivedDate;
  }

  public void setReceivedDate(Date receivedDate) {
    this.receivedDate = receivedDate;
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

  @Override
  public String toString() {
    return "CandidateEntity{" +
            "id=" + id +
            ", cin='" + cin + '\'' +
            ", passportId='" + passportId + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", email='" + email + '\'' +
            ", address='" + address + '\'' +
            ", expDuration=" + expDuration +
            ", profile='" + profile + '\'' +
            ", receivedDate=" + receivedDate +
            ", createdDate=" + createdDate +
            ", status=" + status +
            ", user=" + user +
            '}';
  }
}
