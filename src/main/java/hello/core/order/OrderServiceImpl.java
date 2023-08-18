package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;


@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final   MemberRepository memberRepository;
    private  final DiscountPolicy discountPolicy;


    //   @Autowired
    // /의존관계주입 : 한개일땐 생략가능, 요즘은 거의 생성자 주입
    //@RequiredArgsConstructor 를 사용하면 final이 붙은 필드를 모아 생성자를 알아서 만들어준다
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        System.out.println("memberRepository : " + memberRepository);
        System.out.println("discountPolicy : " + discountPolicy);
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


/*  @Autowired ///수정자주입
    public void setMemberRepository(MemberRepository memberRepository) {
        System.out.println("1111111111111");
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        System.out.println("222222222222");
        this.discountPolicy = discountPolicy;
    }*/
    
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    public MemberRepository getMemberRepository(){
        return memberRepository;
    }

}
