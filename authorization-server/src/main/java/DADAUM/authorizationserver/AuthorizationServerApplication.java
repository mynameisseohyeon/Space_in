package DADAUM.authorizationserver;

import DADAUM.authorizationserver.domain.Client;
import DADAUM.authorizationserver.domain.Member;
import DADAUM.authorizationserver.repository.ClientRepository;
import DADAUM.authorizationserver.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class AuthorizationServerApplication {


	public static void main(String[] args) {
		SpringApplication.run(AuthorizationServerApplication.class, args);
	}

}
