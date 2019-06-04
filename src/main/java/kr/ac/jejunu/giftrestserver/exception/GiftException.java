package kr.ac.jejunu.giftrestserver.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class GiftException extends Exception {
    private int errcode;
    public GiftException(String msg, int code) {
        super(msg);
        this.errcode = code;
    }

    public GiftException(String msg) {
        this(msg, 500);
    }

    public int getErrCode() {
        return errcode;
    }
}
