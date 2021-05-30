package com.ugo.jpatest.domain;

import com.ugo.jpatest.domain.entitylistener.*;
import com.ugo.jpatest.domain.enumType.Gender;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*

Entity 객체 살펴보기

-엔티티는 데이터에 대한 객체이기때문에 DB 레코드를 반영되는 용도로 쓰이지만
-객체로써의 역할도 있기 때문에 본인이 필요한 데이터를 갖을 수도 있다 .
-이때 @Transient 를 사용한다.
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@EntityListeners(value = UserEntityListener.class)
public class User extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String email;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    //JPA에서는 초기화 되어있지 않아도 null이면 빈리스트를 넣어주지만
    //JPA가 아닌 곳에서 참조할때는 해당값이 null일 수있기 때문에
    //npe를 방지하기 위해 빈 리스트를 초기화 해준다.
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",insertable = false,updatable = false)
    //엔티티가 어떤 컬럼으로 지정하게 될지를 정한다 . 안 붙히면 중간테이블이 생긴다 .
    //컬럼명을 따로 정해주지 않으면 컬럼명을 활용해서 만든다.
    //user에서 userHistory를 갖고 있는 것은 올바르지 못하다.
    //insertable updatable 를 false로 해주는 것이 좋다 .
    private List<UserHistory> userHistories = new ArrayList<>();
}
