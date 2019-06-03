package kr.ac.jejunu.giftrestserver.model;

import lombok.Data;

@Data
public class Account {
    private String userId;
    private String fintechAccountNum;
    private String scope;
    private String clientInfo;
}
