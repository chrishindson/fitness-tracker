package com.chhin.fitnesstracker.repository;

import com.chhin.fitnesstracker.entity.FTUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FTUserRepository extends JpaRepository<FTUser, Long> {

  Optional<FTUser> findByUsernameIgnoreCase(@Param("username") String username);
}
