package kr.ac.jejunu.giftrestserver.controller;

import kr.ac.jejunu.giftrestserver.exception.GiftException;
import kr.ac.jejunu.giftrestserver.model.User;
import kr.ac.jejunu.giftrestserver.payload.UserAddPayload;
import kr.ac.jejunu.giftrestserver.payload.ValidatePayload;
import kr.ac.jejunu.giftrestserver.service.BankService;
import kr.ac.jejunu.giftrestserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @PostMapping(value="/validateAccount")
    public Map<String, Object> validateAccount(@RequestBody ValidatePayload account) {
        Map<String, Object> result = null;
        try {
            result = userService.authUser(account);
            result.put("code", 200);
            result.put("messages", "success");
        } catch (GiftException e) {
            return new HashMap<>() {{
                put("code", e.getErrCode());
                put("messages", e.getMessage());
            }};
        }
        return result;
    }

    @GetMapping(value="/account/{user_seq_id}")
    public Map<String, Object> getAccountFromUserSeqId(@PathVariable("user_seq_id") String userSeqId) {


        Optional<User> user = userService.getAccountFromUserSeqId(userSeqId);
        if(user.isEmpty()) {
            return new HashMap<>() {{
                put("code", 400);
                put("messages", "account not founded");
            }};
        }

        return new HashMap<>() {{
            put("code", 200);
            put("messages", "success");
            put("name", user.get().getName());
            put("email", user.get().getEmail());
        }};
    }

    /**
     * 계정 추가
     * @param userAddPayload
     * @return
     */
    @PostMapping(value="/addAccount", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> addAccount(@RequestBody UserAddPayload userAddPayload) {

        Map<String, Object> tokenRes = bankService.getToken(userAddPayload.getAuthCode());

        final String accessToken = (String) tokenRes.get("access_token");
        final String refreshToken = (String) tokenRes.get("refresh_token");
        final String userSeqNo = (String) tokenRes.get("user_seq_no");

        Optional<User> existsUserOptional = userService.getAccountFromUserSeqId(userSeqNo);
        if(existsUserOptional.isPresent()) {
            return new HashMap<>() {{
                put("code", 401);
                put("messages", "account already exists");
            }};
        }
        User user = User.builder()
                .email(userAddPayload.getEmail())
                .name(userAddPayload.getName())
                .password(userAddPayload.getPassword())
                .email(userAddPayload.getEmail())
                .scope(userAddPayload.getScope())
                .refreshToken(refreshToken)
                .userSeqId(userSeqNo)
                .testBalance(500000L)
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
