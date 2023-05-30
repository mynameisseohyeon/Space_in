package teamCCKLJ.spacein.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Client {
    @Id
    private String client_id;
    private String client_secret;
    private String redirect_uri;

    //나중에 분리
    private String user;
    private String scope;
    private String authorization_code;
}

