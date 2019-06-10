package kr.ac.jejunu.giftrestserver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

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
    private Long payDate;
    private Long price;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="game_id")
    public Game game;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="account_num")
    public User user;
}
