package DADAUM.authorizationserver.repository;

import DADAUM.authorizationserver.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional

    //@Rollback(false)
    public void testMember() throws Exception{
        Member member = new Member();
        member.setUser_name("memberA");
        String saveEmail = memberRepository.save(member);
        /*
        Long savedId = memberRepository.save(member);
        Member findMember = memberRepository.find(savedId);

        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUser_name()).isEqualTo(member.getUser_name());

         */
        List<Member> memberList = memberRepository.findAll();
        for (Member findMember : memberList) {
            Assertions.assertThat(findMember.getUser_email()).isEqualTo(member.getUser_email());
        }
    }
}