package kr.ac.jejunu.giftrestserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="game_describe_image")
public class GameDescribeImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String describeImage;
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;
}
