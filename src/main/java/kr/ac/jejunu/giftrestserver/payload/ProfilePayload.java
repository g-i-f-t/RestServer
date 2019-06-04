package kr.ac.jejunu.giftrestserver.payload;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProfilePayload implements Serializable {
    private String name;
    private String email;
}
