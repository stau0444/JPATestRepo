package com.ugo.jpatest.repository;

import com.ugo.jpatest.domain.User;
import org.apache.tomcat.jni.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

    User findByName(String name);
    List<User> findUsersByEmail(String email);
    User findByNameAndEmail(String name,String email);
    List<User> findTop3ByName(String name);

    //이메일 혹은 이름으로 찾는다.
    User findByEmailOrName(String name, String email);

    //after와 before
    Optional<List<User>> findByCreatedAtAfter(LocalDateTime localDateTime);

    List<User> findByCreatedAtBefore(LocalDateTime localDateTime);

    //graterThan LessThan
    List<User> findByCreatedAtGreaterThan(LocalDateTime localDateTime);

    List<User> findByCreatedAtGreaterThanEqual(LocalDateTime localDateTime);

    List<User> findByCreatedAtBetween(LocalDateTime from, LocalDateTime until);

    //like , startingWith , EndWith ,contain
    List<User> findByNameContains(String name);

    //파라미터로 %name% 앞뒤 지정이 가능하다
    List<User> findByNameLike(String like);

    //Like %name 와 같다
    List<User> findByNameStartingWith(String startWith);

    //like name%와 같다
    List<User> findByNameEndingWith(String endingWith);

    //in

    List<User> findByNameIn(List<String> names);

    //NotNull

    List<User> findByIdIsNotNull();


    // TODO: 2021/05/27  더 찾아봐야함
    //NotEmpty

    List<User> findTop3ByNameOrderByIdDesc(String name);

    List<User> findFirstByNameOrderByIdDescEmailAsc(String name);

    List<User> findFirstByName(String name , Sort sort);

    //Page 객체는 페이징 응답값을 담고 Pageble 은 페이지 요청 값을 담는다.
    Page<User> findByName(String name, Pageable pageable);

    //nativeQuery 사용
    @Query(value = "select * from user limit 1",nativeQuery = true)
    Map<String,Object> findRowRecode();

    User findByEmail(String s);
}
