package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


/// 인터페이스(InitializingBean, DisposableBean)를 implements 해서 사용하는 초기화 방법은 이제 거의 안씀
/// @PostConstruct ,@PreDestroy 쓰면됨
// 코드를 고칠 수 없는 외부 라이브러리를 초기화, 종료해야 하면 @Bean 의 initMethod , destroyMethod 를 사용.
public class NetworkClient {

    String url;

    public NetworkClient() {
        System.out.println("생성자 호출, = url =" +url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect(){
        System.out.println("connect : " +url);
    }

    public void call(String message){
        System.out.println("call : " + url + " message = " + message);
    }

    public void disconnect(){
        System.out.println("close : " + url);
    }


    @PostConstruct
    public void init(){
        System.out.println("Network.init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close(){
        System.out.println("Network.close");
        disconnect();
    }
//    @Override ///의존관계 주입이 끝나면 알랴줌
//    public void afterPropertiesSet() throws Exception {
//        System.out.println("afterPropertiesSet ");
//        connect(); //객체 생성시 connect
//        call("초기화 연결 메시지");
//    }
//
//    @Override
//    public void destroy() throws Exception {
//        System.out.println("destroy");
//        disconnect();
//    }
}

