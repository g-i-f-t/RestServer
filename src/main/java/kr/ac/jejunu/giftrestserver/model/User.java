package kr.ac.jejunu.giftrestserver.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user_info")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num")
    @Setter(AccessLevel.NONE)
    private Long userId;
    @Column(name="name")
    private String name;
    @Column(name="password")
    private String password;
    @Column(name="email")
    private String email;
    @Column(name="refresh_token")
    private String refreshToken;
    @Column(name="user_seq_id")
    private String userSeqId;
    @Column(name="scope")
    private String scope;
    @Column(name="test_balance", columnDefinition = "int(11) unsigned DEFAULT 500000")
    private Long testBalance;
}
