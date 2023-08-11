package hello.core;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        ///AppConfig에 @Bean 사용해서 수동으로 등록해놨기때문에 충돌남
        // @Configuration 안에 @Component 붙어있음
        excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {


}
