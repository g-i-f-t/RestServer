package kr.ac.jejunu.giftrestserver.controller;

import kr.ac.jejunu.giftrestserver.payload.BankPayLoad;
import kr.ac.jejunu.giftrestserver.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/bank")
@RequiredArgsConstructor
public class BankController {
    private final BankService bankService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> getBankList(@RequestBody BankPayLoad bankPayLoad) {
        System.out.println(bankPayLoad.getAccessToken() + ", " + bankPayLoad.getUserSeqId());
        Map<String, Object> account = bankService.accountLookup(bankPayLoad.getAccessToken(), bankPayLoad.getUserSeqId());
        System.out.println(account.toString());
        return new HashMap<>() {{
            put("code", 200);
            put("messages", "hi");
            put("data", account.get("res_list"));
        }};
    }
}
