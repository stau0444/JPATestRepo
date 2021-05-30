package com.ugo.jpatest.repository;

import com.ugo.jpatest.domain.BookReviewInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReviewInfoRepository extends JpaRepository<BookReviewInfo,Long> {
}
