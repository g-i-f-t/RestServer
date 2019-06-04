package kr.ac.jejunu.giftrestserver.payload;

import lombok.Data;

import java.io.Serializable;

@Data
public class ValidatePayload implements Serializable {
    private String email;
    private String password;
}
