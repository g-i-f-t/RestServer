package kr.ac.jejunu.giftrestserver.payload;

import lombok.Data;

import java.io.Serializable;

@Data
public class DeveloperPayload implements Serializable {
    private String name;
    private String email;
    private String password;
    private String authCode;
    private String scope;
}
