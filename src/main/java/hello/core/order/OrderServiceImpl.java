package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;


@Component
public class OrderServiceImpl implements OrderService{

     private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /* 필드 인젝션은 비추천
    *  @Autowired private MemberRepository memberRepository;
    *  @Autowired private DiscountPolicy discountPolicy;
    * */


    //   @Autowired
    // /의존관계주입 : 한개일땐 생략가능, 요즘은 거의 생성자 주입
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        System.out.println("memberRepository : " + memberRepository);
        System.out.println("discountPolicy : " + discountPolicy);
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


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
