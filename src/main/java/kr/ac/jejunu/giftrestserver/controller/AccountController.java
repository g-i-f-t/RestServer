package kr.ac.jejunu.giftrestserver.controller;

import kr.ac.jejunu.giftrestserver.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final BankService bankService;

    @PostMapping
    public Map<String, Object> getBankListFromUserSeqId(@RequestBody String userSeqId, @RequestBody String accessToken) {
        Map<String, Object> response = bankService.accountLookup(accessToken, userSeqId);
        System.out.println(response);
        return null;
    }
}
