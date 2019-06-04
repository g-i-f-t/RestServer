package kr.ac.jejunu.giftrestserver.payload;

import lombok.Data;

@Data
public class DeveloperPayload {
    private String name;
    private String email;
    private String password;
    private String authCode;
    private String scope;
}
