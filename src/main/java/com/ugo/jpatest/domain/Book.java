package com.ugo.jpatest.domain;

import com.ugo.jpatest.domain.entitylistener.Auditable;
import lombok.*;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


//BaseEntity가 상위 클래스이기 때문에 상위크래스의 ToString 과 EqualsAndHashCode 를 사용해야
//출력이 잘 된다.
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book extends BaseEntity implements Auditable{

    @Id @GeneratedValue
    private Long id;

    private String name;

    private String author;

}
