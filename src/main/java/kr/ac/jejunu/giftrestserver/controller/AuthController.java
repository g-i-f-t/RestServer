package kr.ac.jejunu.giftrestserver.controller;

import kr.ac.jejunu.giftrestserver.BankService;
import kr.ac.jejunu.giftrestserver.dao.AccountDao;
import kr.ac.jejunu.giftrestserver.vo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@ResponseBody
public class AuthController {

    @GetMapping(value="/auth")
    public Map<String, String> authUser(@RequestParam String code, @RequestParam String scope, @RequestParam String client_info) {
        Map<String, String> res = new HashMap<>();
        res.put("code", code);
        res.put("scope", scope);
        res.put("client_info", scope);
        return res;
    }

    @PostMapping(value="/addAccount")
    public Map<String, Object> addAccount(@RequestBody Account account) {
        new AccountDao().addAccount(account);
        Map<String, Object> res = new HashMap<>();
        res.put("code", 200);
        res.put("messages", "success");
        return res;
    }
}
