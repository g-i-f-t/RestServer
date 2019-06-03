package kr.ac.jejunu.giftrestserver.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user_info")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num")
    private int userId;
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
    @Column(columnDefinition = "DEFAULT 500000")
    private Long testBalance;
}
