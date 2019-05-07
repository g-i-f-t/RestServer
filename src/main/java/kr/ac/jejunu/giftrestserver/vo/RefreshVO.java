package kr.ac.jejunu.giftrestserver.vo;

import lombok.Data;

@Data
public class RefreshVO {
    private String refreshToken;
    private String scope;
    private String userSeqNo;
}
