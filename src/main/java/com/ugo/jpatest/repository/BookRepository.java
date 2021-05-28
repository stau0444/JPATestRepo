package com.ugo.jpatest.repository;

import com.ugo.jpatest.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {

}
