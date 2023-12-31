package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    //MemberService memberService = new MemberServiceImpl(memberRepository);

    MemberService memberService;

    @BeforeEach
    public void beforeEach (){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }


    @Test
    void join(){

        //given
        Member member = new Member(1l, "memberA", Grade.BASIC);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1l);

        //then
        Assertions.assertThat(findMember.getName()).isEqualTo(member.getName());

    }

}
