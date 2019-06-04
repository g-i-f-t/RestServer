package kr.ac.jejunu.giftrestserver.payload;

import lombok.Data;

import java.io.Serializable;

@Data
public class RefreshPayload implements Serializable {
    private String refreshToken;
    private String scope;
    private String userSeqNo;
}
