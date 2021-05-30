package com.ugo.jpatest.domain;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BookReviewInfo extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //JPA에서 연관관계 맵핑이 되어있다면 아이디 값을 따로 정하지 않아도 찾아서 간다 .
    //OneToOne 의 옵션인 Optioal값이 디폴트로 true 여서 값이 존재할 수도 있고 아닐 수도 있기 떄문에
    //outer join 이 된다.
    //optioal이 false 면 null을 허용하지 않음으로  inner join이 나간다.
    @OneToOne(optional = false)
    private Book book;

    //평균 평점과 , 리뷰 수에 대해서 null을 허용한다면 Wrapper타입으로 받아도 되지만.  null체크를 안하면 npe 터진다.
    //아니라면 primitive 로 받는 것이 좋다 (null 값이 0 으로 들어감)
    //primitive - > 컬럼이 not null 로 지정됨
    //Wrapper -> nullable 하게 지정됨
    private float averageReviewScore;

    private int reviewCount;
}
