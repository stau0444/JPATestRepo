package com.ugo.jpatest.domain;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BookReview extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    private Long bookId;

    //평균 평점과 , 리뷰 수에 대해서 null을 허용한다면 Wrapper타입으로 받아도 되지만.  null체크를 안하면 npe 터진다.
    //아니라면 primitive 로 받는 것이 좋다 (null 값이 0 으로 들어감)
    //primitive - > 컬럼이 not null 로 지정됨
    //Wrapper -> nullable 하게 지정됨
    private float averageReviewScore;

    private int reviewCount;
}
