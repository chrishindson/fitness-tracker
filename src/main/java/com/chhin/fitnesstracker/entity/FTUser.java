package com.chhin.fitnesstracker.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.*;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Builder
@RequiredArgsConstructor
@Entity
@AllArgsConstructor
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

  public String getFullName() {
    return this.forename + " " + this.surname;
  }
}
