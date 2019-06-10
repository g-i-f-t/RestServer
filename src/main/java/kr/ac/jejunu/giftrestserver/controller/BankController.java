package kr.ac.jejunu.giftrestserver.controller;

import kr.ac.jejunu.giftrestserver.exception.GiftException;
import kr.ac.jejunu.giftrestserver.model.PayInfo;
import kr.ac.jejunu.giftrestserver.payload.BankPayLoad;
import kr.ac.jejunu.giftrestserver.payload.WithDrawPayLoad;
import kr.ac.jejunu.giftrestserver.service.BankService;
import kr.ac.jejunu.giftrestserver.service.GameService;
import kr.ac.jejunu.giftrestserver.service.PayInfoService;
import kr.ac.jejunu.giftrestserver.service.UserService;
import kr.ac.jejunu.giftrestserver.vo.ResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/bank")
@RequiredArgsConstructor
public class BankController {
    private final BankService bankService;
    private final PayInfoService payInfoService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseVO getBankList(@RequestBody BankPayLoad bankPayLoad) {
        Map<String, Object> account = bankService.accountLookup(bankPayLoad.getAccessToken(), bankPayLoad.getUserSeqId());

        return ResponseVO.builder()
                .code(200)
                .messages("hi")
                .data(account.get("res_list"))
                .build();
    }

    @PostMapping(path="/withdraw", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> withDrawtest(@RequestBody WithDrawPayLoad withDrawPayLoad) {
        payInfoService.addTransaction(withDrawPayLoad);

        // 테스트 베드에서 작동 안됨
//        String testAccessToken = "f45f904a-c37e-4fb1-a9c9-2c74219f184c";
//        String testFintechNum = "101600000169321934052424";
//        withDrawPayLoad.setAccessToken(testAccessToken);
//        withDrawPayLoad.setFintechUseNum(testFintechNum);
//        Map<String, Object> result = bankService.withDraw("G-I-F-T", String.valueOf(withDrawPayLoad.getPrice()), withDrawPayLoad.getAccessToken(), withDrawPayLoad.getFintechUseNum());
        return null;
    }
}
