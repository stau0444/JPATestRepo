package com.ugo.jpatest.domain.entitylistener;

import java.time.LocalDateTime;

public interface Auditable2 {
     void setCreatedAt(LocalDateTime localDateTime);
     void setUpdatedAt(LocalDateTime localDateTime);
}
