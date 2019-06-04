package kr.ac.jejunu.giftrestserver.payload;

import java.io.Serializable;

public class BankPayLoad implements Serializable {
    private String accessToken;
    private String userSeqId;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUserSeqId() {
        return userSeqId;
    }

    public void setUserSeqId(String userSeqId) {
        this.userSeqId = userSeqId;
    }
}
