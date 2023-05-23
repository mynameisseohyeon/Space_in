package DADAUM.authorizationserver.repository;

import DADAUM.authorizationserver.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {
    @PersistenceContext //스프링부트에서 entitiyManager 주입
    private EntityManager em;
    public String save(Member member){
        em.persist(member);
        return member.getUser_email();
    }
    public Member find(String email){
        return em.find(Member.class, email);
    }

    //테스트 안해봄
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
