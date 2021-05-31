package com.ugo.jpatest.repository;

import com.ugo.jpatest.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {

}
