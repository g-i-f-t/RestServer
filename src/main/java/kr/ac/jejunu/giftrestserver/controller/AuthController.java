package kr.ac.jejunu.giftrestserver.controller;

import kr.ac.jejunu.giftrestserver.BankService;
import kr.ac.jejunu.giftrestserver.dao.AccountDao;
import kr.ac.jejunu.giftrestserver.dao.UserDao;
import kr.ac.jejunu.giftrestserver.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.HashMap;
import java.util.Map;

@Controller
@ResponseBody
public class AuthController {

    @Autowired
    BankService bankService;

    @Autowired
    UserDao userdao;


    @GetMapping(value="/auth")
    public Map<String, String> authUser(@RequestParam String code, @RequestParam String scope, @RequestParam String client_info) {
        Map<String, String> res = new HashMap<>();
        res.put("code", code);
        res.put("scope", scope);
        res.put("client_info", scope);
        return res;
    }

    @PostMapping(value="/validateAccount")
    public void validateAccount(@RequestBody ValidateVO account) {
        System.out.println(account.getEmail());
    }

    @GetMapping(value="/account/{user_seq_id}")
    public Map<String, Object> getAccountFromUserSeqId(@PathVariable("user_seq_id") String userSeqId) {
        Map<String, Object> res = new HashMap<>();
        Profile profile = userdao.getAccountFromUserSeqId(userSeqId);

        if(profile == null) {
            res.put("code", "400");
            res.put("messages", "account not founded");
            return res;
        }

        res.put("code", "200");
        res.put("messages", "success");
        res.put("data", profile);
        return null;
    }

    @PostMapping(value="/addAccount", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> addAccount(@RequestBody UserAddVo userAddVo) {
//            @RequestBody String email,
//            @RequestBody String password,
//            @RequestBody String name,
//            @RequestBody String authCode,
//            @RequestBody String scope) {
//        new AccountDao().addAccount(account);

        Map<String, Object> tokenRes = bankService.getToken(userAddVo.getAuthCode());

        final String accessToken = (String) tokenRes.get("access_token");
        final String refreshToken = (String) tokenRes.get("refresh_token");
        final String userSeqNo = (String) tokenRes.get("user_seq_no");

        User user = new User();

        user.setPassword(userAddVo.getPassword());
        user.setName(userAddVo.getName());
        user.setEmail(userAddVo.getEmail());
        user.setScope(userAddVo.getScope());
        user.setRefreshToken(refreshToken);
        user.setUserSeqId(userSeqNo);

        Map<String, Object> res = new HashMap<>();

        try {
            userdao.createUser(user);
        } catch(Exception e) {
            e.printStackTrace();
            res.put("code", 400);
            res.put("messages", "add failed");
            return res;
        }


        res.put("code", 200);
        res.put("messages", "success");
        res.put("access_token", accessToken);
        res.put("user_seq_no", userSeqNo);
        return res;
    }
}
