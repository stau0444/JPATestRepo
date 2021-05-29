package com.ugo.jpatest.support;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

//엔티티 리스너에 빈을 주입할 수 있도록 해주는 클래스
@Component
public class BeanUtils implements ApplicationContextAware {

   private static ApplicationContext applicationContext;

   @Override
   public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
      BeanUtils.applicationContext = applicationContext;
   }

   public static <T> T getBean(Class<T> clazz){
      System.out.println("applicationContext of BeanUtils"+ applicationContext);
      return applicationContext.getBean(clazz);
   }
}
