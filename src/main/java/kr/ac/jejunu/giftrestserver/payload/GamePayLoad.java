package kr.ac.jejunu.giftrestserver.payload;

import kr.ac.jejunu.giftrestserver.model.Game;
import kr.ac.jejunu.giftrestserver.model.GameDescribeImage;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

@Data
public class GamePayLoad implements Serializable {
    private Game game;
    private ArrayList<GameDescribeImage> gameDescribeImages;
    private String userSeqId;
}
