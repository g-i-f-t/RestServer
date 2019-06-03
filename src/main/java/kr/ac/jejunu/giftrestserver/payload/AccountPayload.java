package kr.ac.jejunu.giftrestserver.payload;

import lombok.Data;

@Data
public class AccountPayload {
    private String userId;
    private String fintechAccountNum;
    private String scope;
    private String clientInfo;
}
