package kr.ac.jejunu.giftrestserver.controller;

import kr.ac.jejunu.giftrestserver.service.PayInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/pay")
@RequiredArgsConstructor
public class PayInfoController {

    private final PayInfoService payInfoService;

    @GetMapping("/latest")
    public Map<String, Object> getLatestTransaction() {
        return new HashMap<>() {{
            put("code", 200);
            put("messages", "success");
            put("data", payInfoService.findLatestPayInfo());
        }};
    }
}
