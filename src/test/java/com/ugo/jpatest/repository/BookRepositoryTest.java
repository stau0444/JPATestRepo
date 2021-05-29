package com.ugo.jpatest.repository;

import com.ugo.jpatest.domain.Book;
import org.hibernate.sql.Insert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.beans.Transient;

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

        Book book1 = bookRepository.findById(1L).orElseThrow(RuntimeException::new);
        System.out.println(book1);
        book1.setName("고냥이");
        bookRepository.save(book1);


        Book updatedBook = bookRepository.findById(1L).orElseThrow(RuntimeException::new);

        System.out.println(updatedBook);


        assertNotEquals(book1.getUpdatedAt(),updatedBook.getUpdatedAt());


    }
}