package com.ugo.jpatest.repository;

import com.ugo.jpatest.domain.*;
import com.ugo.jpatest.domain.entitylistener.UserEntityListener;
import com.ugo.jpatest.domain.enumType.Gender;
import org.hibernate.sql.Insert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    UserEntityListener userEntityListener;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    PublisherRepository publisherRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    UserRepository userRepository;



    @Test
    void userEntityListener(){
        User user = new User();
        user.setName("광식이");
        user.setEmail("광식이닷컴");
        userRepository.save(user);
        List<UserHistory> gsi = userRepository.findByName("광식이").getUserHistories();
        gsi.forEach(System.out::println);
        //userEntityListener.prePersistAndPreUpdate(user);
    }

    @Test
    void bookTest(){
        Book book = new Book();
        book.setName("ugobook");
        book.setAuthorId(1L);

        bookRepository.save(book);

        Book book1 = bookRepository.findById(1L).orElseThrow(RuntimeException::new);
        System.out.println(book1);
        book1.setName("고냥이");
        bookRepository.save(book1);


        Book updatedBook = bookRepository.findById(1L).orElseThrow(RuntimeException::new);

        System.out.println(updatedBook);


        assertNotEquals(book1.getUpdatedAt(),updatedBook.getUpdatedAt());


    }

    @Test
    @Transactional
    void bookRelationTest(){
        givenBookAndReview();

        User user = userRepository.findByEmail("tiger@gmail.com");
        System.out.println("Review : " + user.getReviews());
        System.out.println("Book:" + user.getReviews().get(0).getBook());
        System.out.println("Publisher:" + user.getReviews().get(0).getBook().getPublisher());

    }

    private void givenBookAndReview() {
        givenReview(givenUser(),giveBook(givenPublisher()));
    }

    private Publisher givenPublisher(){
        Publisher publisher = new Publisher();
        publisher.setName("TIGER COMPANY");
        return publisherRepository.save(publisher);
    }

    private Book giveBook(Publisher publisher) {
        Book book = new Book();
        book.setName(" TIGER JPA BOOK");
        book.setPublisher(publisher);
        return bookRepository.save(book);
    }

    private User givenUser(){
        return userRepository.findByEmail("tiger@gmail.com");
    }

    private void givenReview(User user , Book book) {
        Review review = new Review();
        review.setTitle("이 책 정말 좋타 .");
        review.setContent("좋은 책이었따.-content-");
        review.setScore(5.0f);
        review.setUser(user);
        review.setBook(book);
        reviewRepository.save(review);
    }

    @Test
    void initDBTest(){
        userRepository.findAll().forEach(System.out::println);
    }
}