package com.ugo.jpatest.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserHistory extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    //joinColumn으로 컬럼을 저징할 때 엔티티 필드명과 맞지 않으면 오류가난다.
    //@Column을 통해 joinColumn명과 필드명을 맞춰줘야한다.
    private Long userId;

    private String name;

    private String email;

}
