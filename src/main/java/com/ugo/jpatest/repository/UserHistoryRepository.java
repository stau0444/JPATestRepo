package com.ugo.jpatest.repository;

import com.ugo.jpatest.domain.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserHistoryRepository extends JpaRepository<UserHistory,Long> {

    List<UserHistory>  findByUserId(Long userId);

}
