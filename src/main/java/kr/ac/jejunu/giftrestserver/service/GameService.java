package kr.ac.jejunu.giftrestserver.service;

import kr.ac.jejunu.giftrestserver.model.Game;
import kr.ac.jejunu.giftrestserver.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    public Collection<Game> getAll() {
        return gameRepository.findAll();
    }

    public Optional<Game> getGameFromId(Long id) {
        return gameRepository.findById(id);
    }

    public void insertGame(Game game) {
        gameRepository.save(game);
    }

    public void updatePrice(Game game, long fundPrice) {
//        System.out.println(game.getCurrentPrice());
        System.out.println(fundPrice);
        Long updatedPrice = game.getCurrentPrice() + fundPrice;
        game.setCurrentPrice(updatedPrice);
        gameRepository.save(game);
    }
}
