package kr.ac.jejunu.giftrestserver.service;

import kr.ac.jejunu.giftrestserver.exception.GiftException;
import kr.ac.jejunu.giftrestserver.model.Developer;
import kr.ac.jejunu.giftrestserver.model.Game;
import kr.ac.jejunu.giftrestserver.model.PayInfo;
import kr.ac.jejunu.giftrestserver.model.User;
import kr.ac.jejunu.giftrestserver.payload.GamePayLoad;
import kr.ac.jejunu.giftrestserver.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GameService {

    private final DeveloperService developerService;
    private final GameRepository gameRepository;
    private final UserService userService;
    private final PayInfoService payInfoService;

    public Collection<Game> getAll() {
        return gameRepository.findAll();
    }

    public Optional<Game> getGameFromId(Long id) {
        return gameRepository.findById(id);
    }

    public void insertGame(GamePayLoad gamePayLoad) throws GiftException {
        Game game = gamePayLoad.getGame();
        gamePayLoad.getGameDescribeImages().parallelStream().forEach(gameDescribeImage -> gameDescribeImage.setGame(game));
        game.setGameDescribeImages(gamePayLoad.getGameDescribeImages());
        Optional<Developer> developerOptional = developerService.getDeveloperByUserSeqId(gamePayLoad.getUserSeqId());
        if(developerOptional.isEmpty()) throw new GiftException("couldn't find developer by userSeqId", 400);
        game.setDeveloper(developerOptional.get());
        gameRepository.save(game);
    }

    public void updatePrice(Game game, long fundPrice) {
//        System.out.println(game.getCurrentPrice());
        System.out.println(fundPrice);
        Long updatedPrice = game.getCurrentPrice() + fundPrice;
        game.setCurrentPrice(updatedPrice);
        gameRepository.save(game);
    }

    public List<String> getAllCategory() {
        return gameRepository.findAllCategory();
    }

    public Collection<Game> getAllBySuccess(boolean b) {
        return gameRepository.findAllBySuccess(b);
    }

    public List<Game> getGameListFromCategory(String category) {
        return gameRepository.findAllByCategory(category);
    }

    public void incrementPrice(Long price, Long gameId) {
        Game game = gameRepository.findById(gameId).get();
        game.setCurrentPrice(game.getCurrentPrice() + price);
        if(game.getCurrentPrice() >= game.getGoalPrice())
            game.setSuccess(true);
        gameRepository.save(game);
    }

    public List<PayInfo> findAllByuserSeqId(String userSeqId) {
        User user = userService.getAccountFromUserSeqId(userSeqId).get();
        return payInfoService.findAllByAccountNum(user.getId());
    }

    public Game findHotGame() {
        return gameRepository.findHotGame().get();
    }

    public List<Game> findAllByDeveloperUserSeqId(String userSeqId) {
        return gameRepository.findAllByDeveloperUserSeqId(userSeqId);
    }
}
