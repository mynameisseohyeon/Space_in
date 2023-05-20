package DADAUM.authorizationserver.repository;

import DADAUM.authorizationserver.domain.Client;
import DADAUM.authorizationserver.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ClientRepository {
    @PersistenceContext //스프링부트에서 entitiyManager 주입
    private EntityManager em;
    public String save(Client client){
        em.persist(client);
        return client.getClient_id();
    }

    public Client find(String id){
        return em.find(Client.class, id);
    }
}
