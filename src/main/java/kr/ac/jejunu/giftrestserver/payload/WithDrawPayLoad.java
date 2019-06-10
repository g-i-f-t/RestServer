package kr.ac.jejunu.giftrestserver.payload;

import lombok.Data;

import java.io.Serializable;

@Data
public class WithDrawPayLoad implements Serializable {
    private Long gameId, price;
    private String fintechUseNum, accessToken, userSeqId;
}
