package com.ugo.jpatest.repository;

import com.ugo.jpatest.domain.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher,Long> {
}
