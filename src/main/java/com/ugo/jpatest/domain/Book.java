package com.ugo.jpatest.domain;

import com.ugo.jpatest.domain.entitylistener.Auditable;
import lombok.*;


import javax.persistence.*;


//BaseEntity가 상위 클래스이기 때문에 상위크래스의 ToString 과 EqualsAndHashCode 를 사용해야
//출력이 잘 된다.
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book extends BaseEntity implements Auditable{

    //시퀀스 전략의 경우 하이버네이트 시퀀스를 사용하기 떄문에 한 시퀀스를 겹처서 사용한다 .
    //identity전략은 테이블 내에서  테이블 별로 autoincreament 기능을 통해 값이 올라가기 떄문에
    //ddl_auto가 created 면 테이블이 초기화되면서 값도 초기화 된다 .

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String category;

    private Long authorId;

    private Long publisherId;

    @OneToOne
    @ToString.Exclude
    private BookReviewInfo bookReviewInfo;

}
