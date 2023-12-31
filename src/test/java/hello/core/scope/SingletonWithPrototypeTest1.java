package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonWithPrototypeTest1 {

     @Test
     void prototypeFind(){

         AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
         PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
         bean1.addCount();
         Assertions.assertThat(bean1.getCount()).isEqualTo(1);

         PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
         bean2.addCount();
         Assertions.assertThat(bean2.getCount()).isEqualTo(1);

     }


     @Test
     void singletonClientUsePrototype(){
         AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class,PrototypeBean.class);
         ClientBean bean1 = ac.getBean(ClientBean.class);
         int count1 = bean1.logic();
         Assertions.assertThat(count1).isEqualTo(1);

         ClientBean bean2 = ac.getBean(ClientBean.class);
         int count2 = bean2.logic();
         Assertions.assertThat(count2).isEqualTo(2);
     }
     
     static class ClientBean{

         private final PrototypeBean prototypeBean; ///생성시점에 주입

         @Autowired
         public ClientBean(PrototypeBean prototypeBean) {
             this.prototypeBean = prototypeBean;
         }

         public int logic(){
             prototypeBean.addCount();
             int count = prototypeBean.getCount();
             return count;
         }
     }


    @Scope("prototype")
    static class PrototypeBean{

        private int count = 0;

        public void addCount(){
            count++;
        }

        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.Init " + this);
        }

        @PreDestroy/// 호출 안되겠지?
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }
    }
}
