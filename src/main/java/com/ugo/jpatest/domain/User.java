package com.ugo.jpatest.domain;

import jdk.jfr.Enabled;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/*

Entity 객체 살펴보기

-엔티티는 데이터에 대한 객체이기때문에 DB 레코드를 반영되는 용도로 쓰이지만
-객체로써의 역할도 있기 때문에 본인이 필요한 데이터를 갖을 수도 있다 .
-이때 @Transient 를 사용한다.
 */

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
@Entity
@Table(
        name = "user",
        indexes = {@Index(columnList = "name")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})}
)
//@Table은 테이블 이름 , 스키마 같은 것을 지정해야 할 경우에 사용한다 .
// name에 지정된 테이블로 해당엔티티가 맵핑된다.
// indexes = DB index를 지정한다 .
// uniquerConstraints = 유니크 여러 컬럼에 제약조건을 지정할 때 사용한다 .
//index 같은 경우에는 DB에 인덱스가 지정되 있지 않다면 동작하지 않는다.
public class User {

    @Id @GeneratedValue
    //@GeneratedValue 의 타입
    //시퀀스 던 auto increase 든 transaction 종료시점에 해당기능이 ID를 채워서 DB에 저장한다.
    //IDENTITY : mysql mariaDB 기반의 데이터 베이스일때 많이 사용한다.(auto increase 기능)
    //SEQUENCE : Oracle, Postgre 처럼 Seqeunce 기반일 대 사용한다.
    //TABLE: DB종류 상관없이 ID값을 저장하는 테이블을 만들어 제공
    //Auto: DB 종류 상관없이 자동으로 맞춰 줌
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String email;

    @Column(updatable = false)
    private String nickName;

    //@Transient - 영속성 컨텍스트에 관리를 받지 않게된다.
    //DB column이 아닌 객체로서 필요한 데이터를 저장할 수 있다. 객체오 생명주기를 같이한다.
    @Transient
    private String testData;

    //기본이 ORDINAL인데 인덱스로 관리하기 Enum이 추가되거나 했을떄 무슨값인지 알 수가 없다 .
    //EnumType.STRING 으로 설정해야한다.
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    //@Column(updatable = false)
    //필드를 지정할 수 있다 @테이블과 비슷하다.
    //nullable - 테이블 생성시 nullable 을 지정해준다 .
    //constraint 와 마찬가지로  이미 생성된 테이블에 nullable 제약이 없다고 추가되거나 하진 않는다.
    //그외에 length , scale definition등 컬럼에 대한 내용을 설정할 수 있다.
    //insertable =false 는 인서트시 해당컬럼 데이터를 넣지 않는다.
    //updatable = false 는 updatetl 해당컬럼 데이터를 넣지 않는다.

    private LocalDateTime createdAt;
    //@Column(insertable = false)
    private LocalDateTime updatedAt;

}
