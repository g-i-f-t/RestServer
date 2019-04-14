package kr.ac.jejunu.giftrestserver.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Game extends GameMinify {
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
}