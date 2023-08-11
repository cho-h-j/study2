package hello.core.member;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    /////MemberServiceImpl 은 MemberRepository, MemoryMemberRepository 둘다 의존하는 문제가 생김 - > 추상화에도 의존 구체화에도 의존
//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    private final MemberRepository memberRepository;


    @Autowired // MemberRepository 타입에 맞는 스프링 빈을 주입  ac.getBean(MemberRepository.class)이랑 비슷
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }


    ///테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
