package DADAUM.authorizationserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {
    private String client_id;
    private String client_secret;
    private String scope;
    private String redirect_uri;
}
