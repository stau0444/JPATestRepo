package com.ugo.jpatest.repository;

import com.ugo.jpatest.domain.BookReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReviewInfoRepository extends JpaRepository<BookReview,Long> {
}
