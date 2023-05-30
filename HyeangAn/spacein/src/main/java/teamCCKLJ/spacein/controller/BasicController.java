package teamCCKLJ.spacein.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import teamCCKLJ.spacein.domain.Client;
import teamCCKLJ.spacein.repository.ClientRepository;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@Slf4j
public class BasicController {
    @Autowired
    ClientRepository clientRepository;
    /* schema.sql, data.sql로 초기데이터 처리
    @GetMapping("/init")
    @ResponseBody
    @Transactional
    public String init(){
        log.info("init......................");
        Client client = new Client();
        client.setClient_id("spacein");
        client.setClient_secret("password");
        client.setRedirect_uri("http://localhost:8080/callback");
        clientRepository.save(client);
        return "클라이언트 본인 정보 등록";
    }
     */
    @GetMapping
    public String oauth2login(){
        return "login";
    }
    @GetMapping("/mapTest")
    public String mapTest(){
        return "test";
    }
    @GetMapping("/test")
    public String test(){
        return "redirect:http://localhost:9000";
    }
    @Transactional
    @GetMapping("/callback")
    public String temp(@RequestParam String authorization_code) throws UnsupportedEncodingException {
        //authorization_code 저장 -> 어디다?
        Client findClient = clientRepository.find("spacein");
        findClient.setAuthorization_code(authorization_code);
        clientRepository.save(findClient);
        String encodeUri = URLEncoder.encode("http://localhost:8080/callback", "UTF-8");
        String redirect_uri = "redirect:http://localhost:9000/token?grant_type=authorization_code&authorization_code="
                + authorization_code + "&redirect_uri=" + encodeUri + "&client_id=spacein&client_secret=password";
                //client_secret 나중에 암호화
        return redirect_uri;
    }
}
