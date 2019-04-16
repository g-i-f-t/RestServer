package kr.ac.jejunu.giftrestserver.vo;

import lombok.Data;

@Data
public class User {
    private int userId;
    private String id;
    private String name;
    private String password;
    private String birthGender;
    private String email;
    private String refreshToken;
    private String userSeqId;
    private String scope;
}
