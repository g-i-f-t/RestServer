package kr.ac.jejunu.giftrestserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="pay_info")
public class PayInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "pay_date", insertable = false, columnDefinition = "NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime payDate;
    private Long price;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="game_id")
    @JsonIgnoreProperties(value = {
            "currentPrice",
            "goalPrice",
            "gameInformation",
            "investInformation",
            "investmentCondition",
            "companyIntroduction",
            "gameDescribeImages"})
    public Game game;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    @JsonIgnore
    public User user;
}
