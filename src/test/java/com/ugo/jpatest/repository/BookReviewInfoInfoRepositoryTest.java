package com.ugo.jpatest.repository;

import com.ugo.jpatest.domain.Book;
import com.ugo.jpatest.domain.BookReviewInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BookReviewInfoInfoRepositoryTest {


    @Autowired
    BookReviewInfoRepository bookReviewInfoRepository;

    @Autowired
    BookRepository bookRepository;

    @Test
    void bookReviewTest(){
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBook(new Book());
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);


        bookReviewInfoRepository.save(bookReviewInfo);

        BookReviewInfo bookReviewInfo1 = bookReviewInfoRepository.findById(1L).orElseThrow(RuntimeException::new);

        System.out.println(bookReviewInfo1);
    }

    @Test
    void crudTest(){

        givenBookReviewInfo(givenBook());

        Book result = bookReviewInfoRepository
                        .findById(1L)
                        .orElseThrow(RuntimeException::new)
                        .getBook();

        System.out.println("result = " + result);

        BookReviewInfo bookReviewInfo = bookRepository.findById(1L).orElseThrow(RuntimeException::new).getBookReviewInfo();

        System.out.println("bookReviewInfo = " + bookReviewInfo);

    }


    private Book givenBook(){
        Book book = new Book();
        book.setName("ugobook");
        book.setAuthorId(1L);
        book.setPublisherId(1L);

        return bookRepository.save(book);
    }

    private void givenBookReviewInfo(Book book){
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBook(book);
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);
        bookReviewInfoRepository.save(bookReviewInfo);

        // 여기서 쿼리를 보면 3방이 나온다
        /*
            1.bookReviewInfo 를 먼저 훓는다 .
            2.찾아낸 reviewInfo안에 book을 훑는다
            3.book에 bookReviewInfo를 훓고 값을 리턴한다 .
         */
    }
}