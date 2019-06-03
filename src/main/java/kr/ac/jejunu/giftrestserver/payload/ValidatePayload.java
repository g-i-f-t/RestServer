package kr.ac.jejunu.giftrestserver.payload;

import lombok.Data;

@Data
public class ValidatePayload {
    private String email;
    private String password;
}
