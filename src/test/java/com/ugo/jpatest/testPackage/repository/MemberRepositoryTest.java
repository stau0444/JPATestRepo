package com.ugo.jpatest.testPackage.repository;

import com.ugo.jpatest.testPackage.domain.Member;
import com.ugo.jpatest.testPackage.domain.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    TeamRepository teamRepository;

    @Test
    void MemberRelationTest(){
        Team team1 = new Team();
        team1.setName("team1");
        Team savedTeam = teamRepository.save(team1);


        Member member1 = new Member();
        member1.setName("member1");
        member1.setTeam(savedTeam);
        team1.addMember(member1);

        Member member2 = new Member();
        member2.setName("member2");
        member2.setTeam(savedTeam);
        team1.addMember(member2);


        memberRepository.saveAll(Arrays.asList(member1,member2));
        teamRepository.save(team1);

        //팀을 통해서 멤버를 가져온다 양방향으로 연결되어 있기 떄문에 가능하다.
        List<Member> members = teamRepository.findById(1L).orElseThrow(RuntimeException::new).getMemberList();
        System.out.println("여기");
        members.forEach(System.out::println);



        //
    }
}