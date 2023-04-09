package com.chhin.fitnesstracker.repository;

import com.chhin.fitnesstracker.entities.ActivityDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityDetailsRepository extends JpaRepository<ActivityDetails, Integer> {

}