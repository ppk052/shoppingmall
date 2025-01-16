package mongnam.spring.shoppingmall.service;

import mongnam.spring.shoppingmall.dto.MemberFormDto;
import mongnam.spring.shoppingmall.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MemberService memberService;

    public Member createMember() {
        MemberFormDto dto = MemberFormDto.builder()
                .address("경기도 안산시")
                .email("test@test.com")
                .password("1111")
                .name("홍길동")
                .build();

        Member member = Member.createMember(dto, passwordEncoder);
        return member;
    }

    @Test
    void saveMemberTest() {
        Member member = createMember();
        System.out.println(member);
        Member member1 = memberService.saveMember(member);
        System.out.println(member1);
    }

    @Test
    @DisplayName("중복 회원 예외 발생 테스트")
    void saveMemberTest2() {
        Member member1 = createMember();
        Member member2 = createMember();
        memberService.saveMember(member1);

        //오류발생 객체 가져오기
        Throwable e = Assertions.assertThrows(IllegalStateException.class, () -> memberService.saveMember(member2));
        Assertions.assertEquals("이미 존재하는 회원입니다.", e.getMessage());
    }

}