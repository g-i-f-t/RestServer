package kr.ac.jejunu.giftrestserver.vo;

import lombok.Data;

@Data
public class User {
    private Long userId;
    private String name;
    private String password;
    private String email;
    private String refreshToken;
    private String userSeqId;
    private String scope;
}
