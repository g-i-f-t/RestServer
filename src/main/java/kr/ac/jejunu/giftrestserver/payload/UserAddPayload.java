package kr.ac.jejunu.giftrestserver.payload;

import lombok.Data;

@Data
public class UserAddPayload {
    private String email;
    private String password;
    private String name;
    private String scope;
    private String authCode;
}
