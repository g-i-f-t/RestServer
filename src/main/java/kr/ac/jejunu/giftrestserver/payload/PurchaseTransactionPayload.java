package kr.ac.jejunu.giftrestserver.payload;

import lombok.Data;

import java.io.Serializable;

@Data
public class PurchaseTransactionPayload implements Serializable {
    private String price;
    private String userSeqNo;
}
