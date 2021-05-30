package com.ugo.jpatest.testPackage.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Team {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "team")
    private List<Member> memberList = new ArrayList<>();

    public  void addMember(Member member){
        this.memberList.add(member);
    }

}
