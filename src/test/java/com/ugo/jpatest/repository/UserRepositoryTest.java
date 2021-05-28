package com.ugo.jpatest.repository;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.internal.JsonFormatter;
import com.ugo.jpatest.domain.Gender;
import com.ugo.jpatest.domain.User;
import com.ugo.jpatest.dto.Content;
import com.ugo.jpatest.dto.PageDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

import static org.springframework.data.domain.Sort.Order.asc;
import static org.springframework.data.domain.Sort.Order.desc;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ObjectMapper objectMapper;

//    @BeforeEach
//    public void insertUser(){
//        for(int i = 1 ; i <=5 ; i++){
//            User user = new User((long) i,"ugo","ugo"+i+".com", LocalDateTime.now(),LocalDateTime.now());
//            userRepository.save(user);
//        }
//    }

    @Test
    @Transactional
    void crud(){

        //findAllById에 List 타입으로 아이디를 넣어주면 해당 아이디 값을 갖는 값을 가져온다
//       List<Long> longs = Arrays.asList(1L, 2L, 3L);
//       List<User> users = userRepository.findAllById(longs);
//
//        User user1 = new User("ugo","ugo.com");
//        User user2 = new User("ugogo","ugogo.com");
//
//
//        //여러개를 save 할때 사용
//        userRepository.saveAll(Arrays.asList(user1,user2));


//        List<User> users = userRepository.findAll();
//        users.forEach(System.out::println);


        //getOne 과 findById 차이
        //getOne 은 기본적으로 razy fetch(proxy)로 구현되어 있다 .
        //User user = userRepository.getOne(1L);


        //findById는 리턴값이 Optional로 랩핑되어 있다
        //findById는 eagar 패치가 기본이기 때문에 값을 바로가져온다
        Optional<User> byId = userRepository.findById(10L);
        //Optioanl으로 값이 없을 경우에 대해 처리할 수 있다
        //찾는 값이 없을 경우 null을 리턴 시킨다
        User user = byId.orElse(null);
        System.out.println(user);
    }

    @Test
    void count(){
        //count 를 가져오는 메서드
        long count = userRepository.count();
        //초기데이터인 5개를 리턴한다.
        System.out.println(count);
    }

    @Test
    void delete(){
        //delete는 기본적으로 삭제하려는 값을 한번 체크하고 삭제하기 떄문에 조회커리 1개 삭제쿼리 1개가 쌍으로 나간다 생각하면 된다.
        //findById로 데이터를 찾고  , 해당 데이터를 삭제한다 쿼리가 2(select , delete)번나간다
        //userRepository.delete(userRepository.findById(1L).orElseThrow(RuntimeException::new));

        //특정 값으로 삭제 명시적으로는 직접 코드가 줄지만 동작은 똑같다. 쿼리가 2번 나간다 .
        //userRepository.deleteById(4L);

        //삭제를 원하는 엔티티들의 id 리스트를 넣어서 삭제한다.
        //지정한 각 엔티티 마다 select 쿼리가 한방씩 나간다 .
        //지금은 3개를 지정했으니까 select3개에  delete3개 총 쿼리는 6개이다
        //userRepository.deleteAllById(Arrays.asList(1L,2L,3L));

        //deleteAll은 모든 걸 지우기 때문에 한번 select 하고
        //delete한다  현재 데이터는 5개이기때문에 select 쿼리 1번 , delete쿼리 5번 총쿼리 6번이 나간다. .
        //userRepository.deleteAll();

        //deleteAllInBatch의 경우 삭제 건에 대해 조회하지 않는다 .
        //또한 delete도 where 절에서 or을 사용해 한번에 삭제한다 .
        //만약 파라미터를 따로 지정하지 않는다 면 쿼리는 delete from user 한번만 나온다.

        userRepository.deleteAllInBatch();
    }

    @Test
    void paging(){
        Page<User> users = userRepository.findAll(PageRequest.of(1,3));
        System.out.println("page :" + users);
        System.out.println("totalElements : " + users.getTotalElements());
        System.out.println("totalPages : "+users.getTotalPages());
        System.out.println("numberOfElement : " + users.getNumberOfElements());
        System.out.println("size : " + users.getSize());
        System.out.println("sort : " + users.getSort());

        users.getContent().forEach(System.out::println);
    }

    @Test
    void exampleMatcher(){
        //예시를 만들어서 matcher를 통해 매칭 시킨다 .
        ExampleMatcher matcher = ExampleMatcher.matching()
                //포함 시키지 않을 필드를 정한다
                .withIgnoreCase("name")
                //매칭시킬 필드를 정하고 조건을 정한다. 여러가지를 정할 수 있다. 아래의 경우 포함하는지를 정한다 email에 com이
                //포함된 엔티티를 찾을 것이다.
                .withMatcher("id", ExampleMatcher.GenericPropertyMatchers.contains());
                        // 예시 역할을 할 가짜 객체와 매쳐를 파라미터로 쿼리를 만든다 .
        Example<User> example = Example.of(new User("ugo","com"),matcher);
        userRepository.findAll(example).forEach(System.out::println);

        //사용이 까다롭다 . 특히 가짜 엔티티를 만들때가 좀 애매한 것 같다 .
    }


    @Test
    @Transactional
    void update(){
        //update도   delete 와 마찬가지로 수정 건에 대해 조회를 한번하고 수정하기 때문에
        //쿼리는 조회 1건 수정 1건 두번이 쌍으로 나간다.
        userRepository.save(new User("ykk","ykk@gmail.com"));
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setEmail("yokoko@gmail.com");
        userRepository.save(user);

        User user2 = userRepository.getOne(1L);
        System.out.println("user2 = " + user2);

        User user1 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        System.out.println(user1);
    }

    @Test
    void queryMethodTest(){
        userRepository.save(new User("고냥이","stau04@gmail.com"));
        userRepository.save(new User("개냥이","stau04@gmail.com"));

        //이름으로 유저를 찾는다.
        User userByName = userRepository.findByName("고냥이");

        //리턴을 리스트 형식으로 받는다.
        List<User> usersByEmail = userRepository.findUsersByEmail("stau04@gmail.com");

        //And도 추가 가능하다
        User userByNameAndEmail= userRepository.findByNameAndEmail("고냥이","stau04@gmail.com");

        //Top 3 를 가져온다 Last는 안되기 때문에 . OrderBy해서 Top을가져온다

        List<User> userTop3 = userRepository.findTop3ByName("ugo");



        //출력

        System.out.println("userByName = " + userByName);

        usersByEmail.forEach(user ->{
            System.out.println("userByEmail : "+user);
        });

        System.out.println("userByNameAndEmail = " + userByNameAndEmail);

        userTop3.forEach(user ->{
            System.out.println("userTop3 : "+user);
        });
    }

    @Test
    void queryMethodTest2(){

        //지금 이후에 생성된 엔티티인가 그럴수 없기 때문에 데이터가 아무 데이터도 리턴되지 않을 것이기 때문에 Optional로 받았다.
        Optional<List<User>> byCreatedAtAfter = userRepository.findByCreatedAtAfter(LocalDateTime.now());

        //지금 이전에 생성된 엔티티를 찾는다 모든 엔티티가 조회될 것이다 .
        List<User> byCreatedAtBefore = userRepository.findByCreatedAtBefore(LocalDateTime.now());


        //어제 에서 오늘 사이에 들어온 엔티티를 조회
        List<User> byCreatedAtBetween = userRepository
                .findByCreatedAtBetween(LocalDateTime.now().minusDays(1L), LocalDateTime.now().plusDays(1L));


        //출력

        //after
        System.out.println("byCreatedAtAfter = " + byCreatedAtAfter.get());


        //before
        byCreatedAtBefore.forEach(user ->{
            System.out.println("UserByCreatedBefore : "+user);
        });

        //like , startingWith , EndWith ,contain
        List<User> byNameLike = userRepository.findByNameLike("%ugo%");
        byNameLike.forEach(user -> {
            System.out.println("byNameLike : " + user);
        });

        List<User> startingWith = userRepository.findByNameStartingWith("ug");
        startingWith.forEach(user -> {
            System.out.println("startingWith : " + user);
        });

        List<User> byNameEndingWith = userRepository.findByNameEndingWith("4");
        byNameEndingWith.forEach(user -> {
            System.out.println("byNameEndingWith : " + user);
        });

        //in
        List<User> byNameIn = userRepository.findByNameIn(Arrays.asList("ugo1","ugo2","ugo3"));
        byNameIn.forEach(user -> {
            System.out.println("byNameIn : " + user);
        });

        //NotNull - 파라미터 없음

        List<User> notNull = userRepository.findByIdIsNotNull();
        notNull.forEach(user -> {
            System.out.println("notNull : " + user);
        });

    }

    @Test
    void sortingWithQueryMethod(){

        //Top3
        List<User> users = userRepository.findTop3ByName("ugo");
        users.forEach(user -> {
            System.out.println("Top3: " + user);
        });

        //Last3

        List<User> ugo = userRepository.findTop3ByNameOrderByIdDesc("ugo");
        ugo.forEach(user -> {
            System.out.println("last: " + user);
        });

        List<User> IdDescEmailAsc = userRepository.findFirstByNameOrderByIdDescEmailAsc("ugo");
        IdDescEmailAsc.forEach(user -> {
            System.out.println("IdDescEmailAsc: " + user);
        });

        //Sort 생성해서 넘겨준다. Order에는 여러가지 넘겨줄 수 있다.
        List<User> firstByNameSort = userRepository.findFirstByName("ugo", Sort.by(desc("id"),asc("email")));
        firstByNameSort.forEach(user -> {
            System.out.println("firstByNameSort: " + user);
        });
    }

    @Test
    void pagingWithQueryMethod() throws JsonProcessingException {
        //PageRequest로 page 요청정보를 담는다 (Pagable 생성)
        Page<User> page = userRepository.findByName("ugo", PageRequest.of(0, 2, Sort.by(desc("id"))));

        List<Content> contents = new ArrayList<>();
        page.getContent().forEach(content->{
            Content cont = new Content();
            cont.setCreatedAt(content.getCreatedAt());
            cont.setEmail(content.getEmail());
            cont.setId(content.getId());
            cont.setName(content.getName());
            cont.setUpdatedAt(content.getUpdatedAt());
            contents.add(cont);
        });
        PageDto pageDto = new PageDto();
        pageDto.setContents(contents);
        pageDto.setTotalPages(page.getTotalPages());
        pageDto.setNumberOfElements(page.getNumberOfElements());
        pageDto.setTotalElements(pageDto.getTotalElements());

        Map<String,Object> jsonPage = new HashMap<>();
        jsonPage.put("json_data",pageDto);
        jsonPage.put("created_by","ugo");
        //Map을 json String으로 변환
        String pageResponse = objectMapper.writeValueAsString(jsonPage);
        //json node 접근
        JsonNode jsonNode = objectMapper.readTree(pageResponse);
        System.out.println("createdBy = " + jsonNode.findValue("createdBy"));
        //json 보기 좋게 변환.
        System.out.println(JsonFormatter.prettyPrint(pageResponse));
    }


    //@Column의 insertable updatable 옵션을 테스트함.
    @Test
    void insertUpdateTest(){
        User user = new User();
        user.setName("ugo");
        user.setEmail("ugo@gmail.com");
        user.setNickName("hohaha");
        userRepository.save(user);

        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user2.setName("ugoasd");
        user2.setNickName("yuhaha");

        //nickname에 updatable false 를 해놨으니 바뀌지 않아야 한다.
        userRepository.save(user2);

        //다시 불러본다.
        User user3 = userRepository.findById(1L).orElseThrow(RuntimeException::new);


        Assertions.assertEquals("hohaha",user3.getNickName());
        System.out.println("user3.getNickName()===="+user3.getNickName());
    }

    @Test
    void enumTest(){
        User user = new User();
        user.setGender(Gender.MALE);
        userRepository.save(user);

        userRepository.findAll().forEach(System.out::println);

        Map<String, Object> rowRecode = userRepository.findRowRecode();
        Object gender = rowRecode.get("gender");
        System.out.println(gender.toString());

    }
}