package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOptionTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(testBean.class);
    }
    
    
    static class testBean{

        /*
        * Autowired의 reauired 옵션의 기본값은 true이기 때문에 자동 주입 대상이 없으면 오류
        * Member는 스프링 빈이 아니다
        * */
        
        @Autowired(required = false) // required = false : 자동 주입할 대상이 없으면 수정자 메서드 자체가 호출 x
        public void setNoBean1(Member noBean1){
            System.out.println("noBean1 : " + noBean1);
        }

        @Autowired              //@Nullable : 자동 주입할 대상이 없으면 null이 입력
        public void setNoBean2(@Nullable Member noBean2){
            System.out.println("noBean2 : " + noBean2);
        }

        @Autowired          //Optional<> : 자동 주입할 대상이 없으면 Optional.empty가 입력
        public void noBean3(Optional<Member> noBean3){
            System.out.println("noBean3 : " + noBean3);
        }

    }
}
