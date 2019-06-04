package kr.ac.jejunu.giftrestserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "developer_info")
public class Developer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    @JsonIgnore
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "refresh_token")
    @JsonIgnore
    private String refreshToken;
    @Column(name = "scope")
    @JsonIgnore
    private String scope;
    @Column(name = "user_seq_id")
    @JsonIgnore
    private String userSeqId;
    @OneToMany
    @JoinColumn(name = "developer_id")
    @JsonIgnore
    private List<Game> gameList;
}
