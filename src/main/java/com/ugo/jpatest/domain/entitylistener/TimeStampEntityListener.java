package com.ugo.jpatest.domain.entitylistener;


import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class TimeStampEntityListener {

    //리스너는 여러 곳에서 사용되고 파라미터로 들어오는 것이 어떤타입인지 모르기 떄문에 Object로 받아야 한다.
    //현재 리스너를 사용하는 것은 Book 과 user 이다 .
    //여러 곳에서 사용하고 있기 떄문에 인터페이스를 만들어 사용하는 곳에서 인터페이스를 구현하게 하면 코드를 줄일 수 있다.
    //만약 인터페이스를 사용하지 않는다면 if ( instanceof )로 타입검사를 하는 것이 리스너를 사용하는 클래스 수만큼 늘어날 것이다.
    @PrePersist
    public void prePersist(Object o){
        if(o instanceof Auditable){
            ((Auditable) o).setCreatedAt(LocalDateTime.now());
            ((Auditable) o).setUpdatedAt(LocalDateTime.now());
        }
    }

    @PreUpdate
    public void preUpdate(Object o ){
        if(o instanceof Auditable){
            ((Auditable) o).setUpdatedAt(LocalDateTime.now());
        }
    }
}
