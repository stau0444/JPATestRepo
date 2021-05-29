package com.ugo.jpatest.repository;

import com.ugo.jpatest.domain.BookReview;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookReviewInfoRepositoryTest {


    @Autowired
    BookReviewInfoRepository bookReviewInfoRepository;

    @Test
    void bookReviewTest(){
        BookReview bookReview = new BookReview();
        bookReview.setBookId(1L);
        bookReview.setAverageReviewScore(4.5f);
        bookReview.setReviewCount(2);

        bookReviewInfoRepository.save(bookReview);

        BookReview bookReview1 = bookReviewInfoRepository.findById(1L).orElseThrow(RuntimeException::new);

        System.out.println(bookReview1);

    }
}