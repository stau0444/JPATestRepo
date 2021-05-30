package com.ugo.jpatest.testPackage.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;

//양 방향 일대일 관계
//객체의 입장에서는 양방향으로 상호 작용하지만 .
//DB 테이블로 봤을때는 서로 단반향으로 연결된 것이다
@Data
@NoArgsConstructor
@Entity
public class Member {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne @JoinColumn(name = "team_id")
    @ToString.Exclude
    private Team team;

}
