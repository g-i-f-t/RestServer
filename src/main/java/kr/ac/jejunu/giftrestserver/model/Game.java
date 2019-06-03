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
public class Game extends GameMinify {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private int gameId;
    private String name;
    private String developer;
    private String category;
    private int currentPrice;
    private int goalPrice;
    private boolean success;
    private String gameInformation;
    private String investigationInformation;
    private String investigationCondition;
    private String companyIntroduction;
    private String profileImage;
    @OneToMany
    @JoinColumn(name = "game_id")
    @JsonIgnoreProperties("game")
    private List<GameDescribeImage> gameDescribeImages;
}
