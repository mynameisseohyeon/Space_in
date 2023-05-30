package DADAUM.authorizationserver.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Client {
    @Id
    private String client_id;
    private String client_secret;
    private String redirect_uri;
    private String user;
    private String scope;
    private String authorization_code;
    private String access_token;
}

