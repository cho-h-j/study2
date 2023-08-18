package hello.core;


import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        ///AppConfig에 @Bean 사용해서 수동으로 등록해놨기때문에 충돌남
        // @Configuration 안에 @Component 붙어있음
        basePackages = "hello.core",
        excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

 /*   @Bean("memoryMemberRepository")
    MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }*/

}
