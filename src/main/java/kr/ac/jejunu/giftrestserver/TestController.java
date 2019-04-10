package kr.ac.jejunu.giftrestserver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping(value="/test")
    public String test() {
        return "index";
    }

}
