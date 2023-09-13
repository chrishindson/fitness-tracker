package com.chhin.fitnesstracker.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Entity
@Table(name = "users")
public class FTUser {

  @Id
  @Column(name = "user_id")
  @SequenceGenerator(name = "users_gen", sequenceName = "users_pk_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_gen")
  private Long userId;

  @Column(name = "username", length = 100, nullable = false, unique = true)
  private String username;
  @Column(name = "forename", length = 100, nullable = false)
  private String forename;
  @Column(name = "surname", length = 100, nullable = false)
  private String surname;
  @Column(name = "password", length = 300, nullable = false, unique = true)
  private String password;
  @Column(name = "email", length = 300, nullable = false, unique = true)
  private String emailAddress;
  @Column(name = "height", nullable = false)
  private Integer height;
  @Column(name = "last_updated")
  private LocalDateTime lastUpdated;
  @Column(name = "created_date", nullable = false)
  private LocalDateTime createdDate;
  @Column(name = "last_accessed_date", nullable = false)
  private LocalDateTime lastAccessedDate;
  @Column(name = "date_of_birth", nullable = false)
  private LocalDate dateOfBirth;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_group_id", nullable = false)
  private UserGroup userGroup;

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setForename(String forename) {
    this.forename = forename;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }

  public void setLastUpdated(LocalDateTime lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public void setLastAccessedDate(LocalDateTime lastAccessedDate) {
    this.lastAccessedDate = lastAccessedDate;
  }

  public void setUserGroup(UserGroup userGroup) {
    this.userGroup = userGroup;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FTUser ftUser = (FTUser) o;
    return Objects.equals(userId, ftUser.userId);
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId);
  }

  public String getFullName() {
    return this.forename + " " + this.surname;
  }

  @Override
  public String toString() {
    return "FTUser{" +
        "userId=" + userId +
        ", username='" + username + '\'' +
        ", forename='" + forename + '\'' +
        ", surname='" + surname + '\'' +
        ", emailAddress='" + emailAddress + '\'' +
        ", createdDate=" + createdDate +
        ", userGroup=" + userGroup +
        '}';
  }
}