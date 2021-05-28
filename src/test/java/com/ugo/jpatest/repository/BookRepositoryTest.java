package com.ugo.jpatest.repository;

import com.ugo.jpatest.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    void bookTest(){
        Book book = new Book();
        book.setName("ugobook");
        book.setAuthor("ugo");

        bookRepository.save(book);

        System.out.println(bookRepository.findAll());
    }
}