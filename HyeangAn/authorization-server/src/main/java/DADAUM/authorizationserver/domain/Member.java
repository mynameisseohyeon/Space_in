package DADAUM.authorizationserver.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Member {
    @Id
    private String user_email;
    private String user_name;
    private String user_password;

}
