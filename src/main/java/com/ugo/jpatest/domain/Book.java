package com.ugo.jpatest.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(value = TimeStampEntityListener.class)
public class Book implements Auditable{

    @Id @GeneratedValue
    private Long id;

    private String name;

    private String author;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    protected boolean canEqual(final Object other) {
        return other instanceof Book;
    }


//    @PrePersist
//    public void prePersist(){
//        this.setCreatedAt(LocalDateTime.now());
//        this.setUpdatedAt(LocalDateTime.now());
//    }
//
//    @PreUpdate
//    public void preUpdate(){
//        this.setUpdatedAt(LocalDateTime.now());
//    }
}
