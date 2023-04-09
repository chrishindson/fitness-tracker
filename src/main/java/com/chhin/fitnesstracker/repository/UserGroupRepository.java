package com.chhin.fitnesstracker.repository;

import com.chhin.fitnesstracker.entities.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGroupRepository extends JpaRepository<UserGroup, Integer> {

}