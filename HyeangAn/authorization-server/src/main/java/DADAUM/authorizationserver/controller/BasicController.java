package DADAUM.authorizationserver.controller;

import DADAUM.authorizationserver.domain.Client;
import DADAUM.authorizationserver.domain.Member;
import DADAUM.authorizationserver.model.ClientDTO;
import DADAUM.authorizationserver.model.MemberDTO;
import DADAUM.authorizationserver.repository.ClientRepository;
import DADAUM.authorizationserver.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.http.HttpResponse;
import java.util.List;

@Controller
@Slf4j
public class BasicController {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    MemberRepository memberRepository;
    //클라이언트가 Resource Server에 자신의 애플리케이션을 등록
    @GetMapping("/client/register")
    @ResponseBody
    @Transactional
    public String register(){
        log.info("init......................");
        Client client = new Client();
        client.setClient_id("spacein");
        client.setClient_secret("password");
        client.setRedirect_uri("http://localhost:8080/callback");
        clientRepository.save(client);
        return "클라이언트 어플리케이션 등록 완료";
    }
    //사용자 로그인
    @GetMapping("/user/login")
    public String user_login(){
        return "login";
    }
    @PostMapping("/user/check")
    public String user_check(Model model, @ModelAttribute MemberDTO memberDTO, @CookieValue String client_id,
                             @CookieValue String client_scope, @CookieValue String client_redirect_uri){
        log.info("********user_check****************** ");
        //모델이 문제인데?
        model.addAttribute("client_scope", client_scope);
        model.addAttribute("user_email", memberDTO.getUser_email());

        Member findMember = memberRepository.find(memberDTO.getUser_email());
        log.info("user_password = {} database_password = {}", findMember.getUser_password(), memberDTO.getUser_password());
        if (findMember.getUser_password().equals(memberDTO.getUser_password())){
            //로그인 성공 -> client id, redirect_uri 확인 -> 다른면 작업x
            log.info("로그인 성공");
            Client findClient = clientRepository.find(client_id);
            log.info("client_uri = {} database_uri ={} ", client_redirect_uri, findClient.getRedirect_uri());
            if (findClient.getRedirect_uri().equals(client_redirect_uri)) {
                //같으면 scope에 대한 권한 허용 메시시->허용하면 user는 scope~~허용
                log.info("같아************************************************************************************");
                return "permission";
            }
            else{
                log.info("달라*************************************************************************************");
            }
        }
        else{
            //로그인실패
            log.info("로그인 실패");
        }
        return "로그인 후 화면";
    }

    //사용자 회원가입
    @GetMapping("/user/signup")
    public String user_signup(){
        return "signup";
    }
    @Transactional
    @PostMapping("/user/save")
    public String user_save(@ModelAttribute MemberDTO memberDTO){
        Member member = new Member();
        member.setUser_name(memberDTO.getUser_name());
        member.setUser_email(memberDTO.getUser_email());
        member.setUser_password(memberDTO.getUser_password());
        memberRepository.save(member);
        return "sign";
    }
    @PostMapping("/login-request")
    public String login(@ModelAttribute ClientDTO clientDTO, HttpSession session, HttpServletResponse response){
        log.info("client id={}", clientDTO.getClient_id());
        log.info("client scope={}", clientDTO.getScope());
        log.info("client redirect_uri={}", clientDTO.getRedirect_uri());

        session.setAttribute("client_id", clientDTO.getClient_id());
        session.setAttribute("client_scope", clientDTO.getScope());
        session.setAttribute("client_redirect_uri", clientDTO.getRedirect_uri());

        Cookie clientIdCookie = new Cookie("client_id", clientDTO.getClient_id());
        Cookie clientScopeCookie = new Cookie("client_scope", clientDTO.getScope());
        Cookie clientRedirectUriCookie = new Cookie("client_redirect_uri", clientDTO.getRedirect_uri());

        // 쿠키 속성 설정 (옵션)
        clientIdCookie.setMaxAge(600); // 쿠키의 유효 시간을 설정 (초 단위)
        clientIdCookie.setPath("/"); // 쿠키의 유효 경로를 설정

        // 쿠키를 응답에 추가
        response.addCookie(clientIdCookie);
        response.addCookie(clientScopeCookie);
        response.addCookie(clientRedirectUriCookie);
        //로그인 안되어있을때 로그인 Pass
        return "sign";
    }

    @ResponseBody
    @GetMapping("test")
    public String test(){
        return "ok";
    }

    //권한(scpoe)저장
    @Transactional
    @PostMapping("/permission/save/{user_email}")
    public String save_permission(@PathVariable String user_email, @CookieValue String client_id,
                                  @CookieValue String client_scope, @CookieValue String client_redirect_uri){
        //유저 아이디, scope 범위 에대해서 허용했는것을 RS 저장
        Client findClient = clientRepository.find(client_id);
        findClient.setUser(user_email);
        findClient.setScope(client_scope);
        String authorization_code = "ac";
        findClient.setAuthorization_code(authorization_code);
        clientRepository.save(findClient);
        String redirect_uri = "redirect:" + client_redirect_uri + "?authorization_code=" + authorization_code;
        return redirect_uri;
    }
    //authorization_code코드를가지고 client_secret, redirect uri client_id 값을 체크해서 일치하면 토큰 생성
    @ResponseBody
    @GetMapping("/token")
    public String token(@RequestParam String authorization_code, @RequestParam String redirect_uri
                        ,@RequestParam String client_id, @RequestParam String client_secret){
        Client findClient = clientRepository.find(client_id);
        log.info(redirect_uri);
        //클라이언트에게 발급한 임시 코드가 맞으면
        //authorization 코드 삭제 accesstoken 발급 저장해서 클라인트에 전달
        if (findClient.getAuthorization_code().equals(authorization_code) &&
                findClient.getClient_secret().equals(client_secret) &&
                findClient.getRedirect_uri().equals(redirect_uri)){
            log.info("accesstokent 발급");
            //********************************
            //토큰 발급 저장 로직 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            //********************************
        }else{
            log.info("accesstokent 발급거부");
        }
        return "토큰 발급";
    }
    /*
    @GetMapping("/login.html")
    public String handleLoginSuccess(HttpSession session){
        // 세션에서 pathVariable 값 가져오기
        String clientId = (String) session.getAttribute("clientId");
        String scope = (String) session.getAttribute("scope");
        String redirectUri = (String) session.getAttribute("redirectUri");

        // pathVariable 값을 사용하여 다시 해당 함수로 리다이렉트
        //return "login";
        //return "redirect:/" + clientId + "/" + scope + "/" + redirectUri;
        return "dd";
    }
     */

}
