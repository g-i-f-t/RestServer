package kr.ac.jejunu.giftrestserver;

import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ClientKey {
    private final String CLIENT_ID = "l7xx7ca57b2adb1340a5b5c982e87fbc44b1";
    private final String CLIENT_SECRET = "79411139871a48409726bbb4eb3989f5";
    private final String REDIRECT_URI = "http://117.17.102.139:8080/auth";
}