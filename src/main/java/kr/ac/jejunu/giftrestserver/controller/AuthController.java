package kr.ac.jejunu.giftrestserver.controller;

import kr.ac.jejunu.giftrestserver.service.BankService;
import kr.ac.jejunu.giftrestserver.repository.UserDao;
import kr.ac.jejunu.giftrestserver.model.User;
import kr.ac.jejunu.giftrestserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final BankService bankService;
    private final UserService userService;


    /**
     * 인증용 더미 페이지
     * @param code
     * @param scope
     * @param client_info
     * @return
     */
    @GetMapping(value="/auth")
    public String authUser(@RequestParam String code, @RequestParam String scope, @RequestParam String client_info) {
        return "인증 중입니다.";
    }

    /**
     * 계정 추가
     * @param password
     * @param name
     * @param email
     * @param authCode
     * @param scope
     * @return
     */
    @PostMapping(value="/addAccount", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> addAccount(
            @RequestBody String password,
            @RequestBody String name,
            @RequestBody String email,
            @RequestBody String authCode,
            @RequestBody String scope) {

        Map<String, Object> tokenRes = bankService.getToken(authCode);

        final String accessToken = (String) tokenRes.get("access_token");
        final String refreshToken = (String) tokenRes.get("refresh_token");
        final String userSeqNo = (String) tokenRes.get("user_seq_no");

        User user = User.builder()
                .email(email)
                .name(name)
                .password(password)
                .email(email)
                .scope(scope)
                .refreshToken(refreshToken)
                .userSeqId(userSeqNo)
                .build();
        try {
            userService.addUser(user);
        } catch(Exception e) {
            return new HashMap<>() {{
                put("code", 400);
                put("messages", "add failed");
            }};
        }

        return new HashMap<>() {{
            put("code", 200);
            put("messages", "success");
            put("access_token", accessToken);
            put("user_seq_no", userSeqNo);
        }};
    }
}
