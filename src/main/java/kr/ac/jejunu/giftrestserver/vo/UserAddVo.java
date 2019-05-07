package kr.ac.jejunu.giftrestserver.vo;

import lombok.Data;

@Data
public class UserAddVo {
    private String email;
    private String password;
    private String name;
    private  String scope;
    private String authCode;
}
