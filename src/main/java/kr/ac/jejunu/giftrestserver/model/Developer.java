package kr.ac.jejunu.giftrestserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "developer_info")
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "refresh_token")
    private String refreshToken;
    @Column(name = "scope")
    private String scope;
    @Column(name = "user_seq_id")
    private String userSeqId;
    @OneToMany
    @JoinColumn(name = "developer_id")
    @JsonIgnore
    private List<Game> gameList;
}
