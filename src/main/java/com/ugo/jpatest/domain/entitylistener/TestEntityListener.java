package com.ugo.jpatest.domain.entitylistener;

import ch.qos.logback.core.joran.conditional.IfAction;
import com.ugo.jpatest.domain.Book;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.lang.management.LockInfo;
import java.time.LocalDateTime;

public class TestEntityListener {

    @PrePersist
    public void prePersist(Object o){
        if(o instanceof Auditable2){
               ((Auditable2) o).setCreatedAt(LocalDateTime.now());
               ((Auditable2) o).setUpdatedAt(LocalDateTime.now());
        }
    }

    @PreUpdate
    public void preUpdate(Object o){
        if (o instanceof Auditable2){
            ((Auditable2) o).setUpdatedAt(LocalDateTime.now());
        }
    }

}
