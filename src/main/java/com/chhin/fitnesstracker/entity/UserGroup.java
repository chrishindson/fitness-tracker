package com.chhin.fitnesstracker.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Builder
@RequiredArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "user_group")
public class UserGroup {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_group_gen")
  @SequenceGenerator(
      name = "user_group_gen",
      sequenceName = "user_group_pk_seq",
      allocationSize = 1)
  @Column(name = "user_group_id", nullable = false)
  private Long userGroupId;

  @Column(name = "user_group_description", nullable = false, length = 50)
  private String userGroupDescription;
}
