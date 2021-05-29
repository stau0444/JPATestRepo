package com.ugo.jpatest.domain;

import com.ugo.jpatest.domain.entitylistener.Auditable2;
import com.ugo.jpatest.domain.entitylistener.TestEntityListener;
import com.ugo.jpatest.domain.entitylistener.UserEntityListener;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserHistory extends BaseEntity{

    @Id @GeneratedValue
    private Long id;
    private Long userId;
    private String name;
    private String email;


}
