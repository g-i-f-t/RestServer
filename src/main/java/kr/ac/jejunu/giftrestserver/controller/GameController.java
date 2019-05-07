package kr.ac.jejunu.giftrestserver.controller;

import kr.ac.jejunu.giftrestserver.dao.GameDao;
import kr.ac.jejunu.giftrestserver.vo.Game;
import kr.ac.jejunu.giftrestserver.vo.GameMinify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
public class GameController {

    @Autowired
    private GameDao gameDao;

    @GetMapping(value="/game")
    public Map<String, Object> getGameList(@RequestParam String list) {
        Map<String, Object> res = new HashMap<>();
        Collection<GameMinify> collection = null;
        switch(list) {
            case "available":
                collection = gameDao.getGameCollection(true);
                break;
            case "done":
                collection = gameDao.getGameCollection(false);
                break;
            default:
                res.put("code", 400);
                res.put("messages", "unavailable list name.");
                return res;
        }
        res.put("code", 200);
        res.put("messages", "success");
        res.put("data", collection);

        return res;
    }

    @GetMapping(value="/game/{id}")
    public Map<String, Object> getGameFromId(@PathVariable Long id) {
        Game game = gameDao.getGameFromId(id);
        Map<String, Object> res = new HashMap<>();

        res.put("code", 200);
        res.put("messages", "success");
        res.put("data", game);
        return res;
    }

    @PutMapping(value="/game/insert")
    public Map<String, Object> insertGame(@RequestBody Game game) {
        gameDao.insertGame(game);
        Map<String, Object> res = new HashMap<>();
        res.put("code", 200);
        res.put("message", "Successfully added");
        return res;
    }


}
