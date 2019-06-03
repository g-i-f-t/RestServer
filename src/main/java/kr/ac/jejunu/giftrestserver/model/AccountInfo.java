package kr.ac.jejunu.giftrestserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "account_info")
public class AccountInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;
    private String scope;
    private String fintechAccountNum;
    private String ClientInfo;
}
