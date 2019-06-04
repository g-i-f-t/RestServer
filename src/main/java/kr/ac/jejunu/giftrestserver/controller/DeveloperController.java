package kr.ac.jejunu.giftrestserver.controller;

import kr.ac.jejunu.giftrestserver.exception.GiftException;
import kr.ac.jejunu.giftrestserver.payload.DeveloperPayload;
import kr.ac.jejunu.giftrestserver.payload.ValidatePayload;
import kr.ac.jejunu.giftrestserver.repository.DeveloperRepository;
import kr.ac.jejunu.giftrestserver.service.BankService;
import kr.ac.jejunu.giftrestserver.service.DeveloperService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/developer")
@RequiredArgsConstructor
public class DeveloperController {

    private final DeveloperService developerService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> addDeveloper(@RequestBody DeveloperPayload developerPayload) {
        Map<String, Object> responseMap = null;
        try {
            responseMap = developerService.addDeveloper(developerPayload);
            responseMap.put("code", "200");
            responseMap.put("messages", "success");
        } catch (GiftException e) {
            responseMap = new HashMap<>() {{
                put("code", e.getErrCode());
                put("messages", e.getMessage());
            }};
        }
        return responseMap;
    }

    @PostMapping("/auth")
    public Map<String, Object> authDeveloper(@RequestBody ValidatePayload account) {
        Map<String, Object> result = null;
        try {
            result = developerService.authDeveloper(account);
            result.put("code", 200);
            result.put("messages", "success");
        } catch (GiftException e) {
            result = new HashMap<>();
            result.put("code", e.getErrCode());
            result.put("messages", e.getMessage());
        }
        return result;
    }

}
