package com.capgemini.candidateorganizationsystem.model;

import javax.persistence.*;

@Entity
@Table(name = "candidates")
public class Candidate {

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

  @Column(name = "mailAddress")
  private String mailAddress;

  @Column(name = "address")
  private String address;

  @Column(name = "expDuration")
  private int expDuration;

  @Column(name = "profile")
  private String profile;

  public Candidate() {

  }

  public Candidate(String firstName, String lastName, String phoneNumber, String mailAddress, String address, int expDuration, String profile) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.mailAddress = mailAddress;
    this.address = address;
    this.expDuration = expDuration;
    this.profile = profile;
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

  public String getMailAddress() {
    return mailAddress;
  }

  public void setMailAddress(String mailAddress) {
    this.mailAddress = mailAddress;
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

  @Override
  public String toString() {
    return "Candidate{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", mailAddress='" + mailAddress + '\'' +
            ", address='" + address + '\'' +
            ", expDuration=" + expDuration +
            ", profile='" + profile + '\'' +
            '}';
  }
}
