package kr.ac.jejunu.giftrestserver.payload;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserAddPayload implements Serializable {
    private String email;
    private String password;
    private String name;
    private String scope;
    private String authCode;
}
