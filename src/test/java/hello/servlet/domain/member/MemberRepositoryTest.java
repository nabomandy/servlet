package hello.servlet.domain.member;

import org.assertj.core.api.AssertionInfo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

//public 생략 가능
public class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();
    //싱글톤이라 new MemberRepository 안됨 ㅋ

    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
        //테스트가 끝나면 깔끔하게 초기화하려고 넣는거
        //테스트는 순서보장이 안되기 때문에
    }

    @Test
    void save() {
        //given 이런게 주어졌을 때
        Member member = new Member("hello",20);

        //when 이런걸 실행했을 때
        Member savedMember = memberRepository.save(member);

        //then 결과가 이거여야 된다.
        Member findMember = memberRepository.findById(savedMember.getId());
        assertThat(findMember).isEqualTo(savedMember);
    }

    @Test
    void findAll() {
        //given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);

        memberRepository.save(member1);
        memberRepository.save(member2);
        
        //when
        List<Member> result = memberRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1, member2);


    }

}
