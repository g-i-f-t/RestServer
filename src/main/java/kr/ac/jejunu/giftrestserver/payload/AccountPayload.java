package kr.ac.jejunu.giftrestserver.payload;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountPayload implements Serializable {
    private String userId;
    private String fintechAccountNum;
    private String scope;
    private String clientInfo;
}
