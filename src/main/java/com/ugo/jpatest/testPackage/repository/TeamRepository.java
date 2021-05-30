package com.ugo.jpatest.testPackage.repository;

import com.ugo.jpatest.testPackage.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team,Long> {
}
