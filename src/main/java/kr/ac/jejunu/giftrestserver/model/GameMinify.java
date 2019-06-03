package kr.ac.jejunu.giftrestserver.model;

import lombok.Data;

@Data
public class GameMinify {
    private int gameId;
    private String name;
    private String developer;
    private String category;
    private int currentPrice;
    private int goalPrice;
    private boolean success;
}
