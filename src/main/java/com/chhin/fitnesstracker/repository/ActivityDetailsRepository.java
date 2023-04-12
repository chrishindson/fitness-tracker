package com.chhin.fitnesstracker.repository;

import com.chhin.fitnesstracker.entities.ActivityDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityDetailsRepository extends JpaRepository<ActivityDetails, Long> {

}