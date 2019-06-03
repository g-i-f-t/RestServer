package kr.ac.jejunu.giftrestserver.payload;

import lombok.Data;

@Data
public class PurchaseTransactionPayload {
    private String price;
    private String userSeqNo;
}
