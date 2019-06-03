package kr.ac.jejunu.giftrestserver.payload;

import lombok.Data;

@Data
public class RefreshPayload {
    private String refreshToken;
    private String scope;
    private String userSeqNo;
}
