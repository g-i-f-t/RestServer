package kr.ac.jejunu.giftrestserver.controller;

import kr.ac.jejunu.giftrestserver.model.Game;
import kr.ac.jejunu.giftrestserver.model.GameMinify;
import kr.ac.jejunu.giftrestserver.repository.GameDao;
import kr.ac.jejunu.giftrestserver.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping(value="/game")
    public Map<String, Object> getGameList() {
        Collection<Game> gameCollection = gameService.getAll();

        return new HashMap<>() {{
            put("code", 200);
            put("messages", "success");
            put("data", gameCollection);
        }};
    }

    @GetMapping(value="/game/{id}")
    public Map<String, Object> getGameFromId(@PathVariable Long id) {
        Optional<Game> game = gameService.getGameFromId(id);
        Map<String, Object> res = new HashMap<>();

        res.put("code", 200);
        res.put("messages", "success");
        res.put("data", game);
        return res;
    }

    @PutMapping(value="/game/insert")
    public Map<String, Object> insertGame(@RequestBody Game game) {
        gameService.insertGame(game);
        Map<String, Object> res = new HashMap<>();
        res.put("code", 200);
        res.put("message", "Successfully added");
        return res;
    }


}
