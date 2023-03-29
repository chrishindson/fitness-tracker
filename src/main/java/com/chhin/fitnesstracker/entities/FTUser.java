package com.chhin.fitnesstracker.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class FTUser {
  @Id
  @Column(name = "user_id")
  @SequenceGenerator(name = "users_pk_seq", sequenceName = "users_pk_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_pk_seq")
  private Integer userId;
}