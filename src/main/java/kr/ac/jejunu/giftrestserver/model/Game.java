package kr.ac.jejunu.giftrestserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "game_info")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private Long gameId;
    private String name;
    private String category;
    private Long currentPrice;
    private Long goalPrice;
    private boolean success;
    private String gameInformation;
    @Column(name = "investigation_information")
    private String investmentInformation;
    @Column(name = "investigation_condition")
    private String investmentCondition;
    private String companyIntroduction;
    private String profileImage;
    @OneToMany
    @JoinColumn(name = "game_id")
    @JsonIgnoreProperties(value = {"game", "id"})
    private List<GameDescribeImage> gameDescribeImages;
    @ManyToOne
    @JoinColumn(name = "developer_id")
    @JsonIgnoreProperties(value = {"password", "email", "refresh_token", "scope", "user_seq_id", "id"})
    private Developer developer;
}
