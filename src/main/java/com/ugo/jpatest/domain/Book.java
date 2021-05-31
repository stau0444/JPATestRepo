package com.ugo.jpatest.domain;

import com.ugo.jpatest.domain.entitylistener.Auditable;
import lombok.*;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


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


    @OneToOne(mappedBy = "book")
    //연관관계의 주인 mappedBy 옵션을 갖게되고 , 반대편에 어떤 것으로 맵핑이 되어 있는지를 알려줘야한다.
    //양방향 관계에서 Tostring을 순환 참조하기 때문에 한방향으로만 하던지
    //@ToString.Exclude  로 toString 을 포함시키지 않게한다
    @ToString.Exclude
    private BookReviewInfo bookReviewInfo;

    @OneToMany
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();

    @ManyToOne
    @ToString.Exclude
    private Publisher publisher;

}
