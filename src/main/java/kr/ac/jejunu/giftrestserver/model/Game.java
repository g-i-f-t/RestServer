package kr.ac.jejunu.giftrestserver.model;

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
    private String developer;
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
    @JsonIgnoreProperties("game")
    private List<GameDescribeImage> gameDescribeImages;
}
