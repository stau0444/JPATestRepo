package com.ugo.jpatest.domain.entitylistener;

import com.ugo.jpatest.domain.User;
import com.ugo.jpatest.domain.UserHistory;
import com.ugo.jpatest.repository.UserHistoryRepository;
import com.ugo.jpatest.support.BeanUtils;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;


@Component
//엔티티 리스너는 빈을 주입 받을 수 없다 .
public class UserEntityListener  {

    @PostPersist
    @PostUpdate
    public void prePersistAndPreUpdate(Object o){

        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);
        User user = (User) o;

        UserHistory userHistory = new UserHistory();
        userHistory.setName(user.getName());
        userHistory.setEmail(user.getEmail());
        userHistory.setUser(user);

        userHistoryRepository.save(userHistory);
    }

}
