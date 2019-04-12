package kr.ac.jejunu.giftrestserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GameController {

    @GetMapping(value="/game")
    public Map<String, Object> getGameList() {

    }

}
