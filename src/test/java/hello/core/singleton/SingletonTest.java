package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {


    @Test
    @DisplayName("onlyJava")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();///호출할때마다 객체 새로 생성되서 문제 -> 메모리 낭비가 심하다 - > 해결방안: 해당 객체가 딱 1개만 생성되고 공유하도록 설계(싱글톤 패턴)
        MemberService memberService = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        System.out.println("memberService " + memberService);
        System.out.println("memberService2 " + memberService2);

        Assertions.assertThat(memberService).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("use Singleton")
    void singletonServiceTest(){
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 : " +singletonService1);
        System.out.println("singletonService2 : " +singletonService2);

        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
    }

    @Test
    @DisplayName("spring container and singleton")
    void springContainer(){

   //     AppConfig appConfig = new AppConfig();
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        System.out.println("memberService " + memberService);
        System.out.println("memberService2 " + memberService2);

        Assertions.assertThat(memberService).isSameAs(memberService2);
    }

}
