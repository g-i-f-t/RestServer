package kr.ac.jejunu.giftrestserver.controller;

import kr.ac.jejunu.giftrestserver.model.Game;
import kr.ac.jejunu.giftrestserver.service.GameService;
import kr.ac.jejunu.giftrestserver.payload.PurchaseTransactionPayload;
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

    public Map<String, Object> getGameList(@RequestParam String list) {
        Map<String, Object> res = new HashMap<>();
        Collection<Game> collection = gameService.getAll();
        switch(list) {
            case "available":
                collection = gameService.getAll();
                break;
            case "done":
                collection = gameService.getAll();
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
        Optional<Game> game = gameService.getGameFromId(id);
        Map<String, Object> res = new HashMap<>();

        res.put("code", 200);
        res.put("messages", "success");
        res.put("data", game);
        return res;
    }

    @PostMapping(value="/game/{id}")
    public Map<String, Object> fundGameFromId(@PathVariable Long id, @RequestBody PurchaseTransactionPayload pt) {
        Map<String, Object> res = new HashMap<>();
        Optional<Game> gameOptional = gameService.getGameFromId(id);

        // id 에 해당하는 게임이 없으면?
        if(gameOptional.isEmpty()) return new HashMap<>() {{
            put("code", 400);
            put("messages", "game was null");
        }};

        Game game = gameOptional.get();

        // 사용자가 금액을 요상하게 입력했을 때
        Long fundPrice = null;
        try {
            fundPrice = Long.getLong(pt.getPrice());
        } catch (Exception e) {
            return new HashMap<>() {{
                put("code", 401);
                put("messages", "Wrong number Input");
            }};
        }

        // 가격 갱신
        gameService.updatePrice(game, fundPrice);

        Optional<Game> updatedGameOptional = gameService.getGameFromId(id);
        if(updatedGameOptional.isEmpty()) return new HashMap<>() {{
            put("code", 400);
            put("messages", "game was null");
        }};
        Game updatedGame = updatedGameOptional.get();

        if(!updatedGame.getCurrentPrice().equals(game.getCurrentPrice())) {
            res.put("code", 402);
            res.put("messages", "transaction failed");
            return res;
        }

        res.put("code", 200);
        res.put("messages", "transaction succeeded");
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
