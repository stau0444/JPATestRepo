package com.ugo.jpatest.testPackage.repository;


import com.ugo.jpatest.testPackage.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {


}
