package com.chhin.fitnesstracker.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "user_group")
public class UserGroup {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_group_gen")
  @SequenceGenerator(name = "user_group_gen", sequenceName = "user_group_pk_seq", allocationSize = 1)
  @Column(name = "user_group_id", nullable = false)
  private Long userGroupId;

  @Column(name = "user_group_description", nullable = false, length = 50)
  private String userGroupDescription;

  public Long getUserGroupId() {
    return userGroupId;
  }

  public void setUserGroupId(Long userGroupId) {
    this.userGroupId = userGroupId;
  }

  public String getUserGroupDescription() {
    return userGroupDescription;
  }

  public void setUserGroupDescription(String userGroupDescription) {
    this.userGroupDescription = userGroupDescription;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserGroup userGroup = (UserGroup) o;
    return Objects.equals(userGroupId, userGroup.userGroupId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userGroupId);
  }

  @Override
  public String toString() {
    return "UserGroup{" +
        "userGroupId=" + userGroupId +
        ", userGroupDescription='" + userGroupDescription + '\'' +
        '}';
  }
}