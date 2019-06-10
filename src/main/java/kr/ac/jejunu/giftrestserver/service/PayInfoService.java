package kr.ac.jejunu.giftrestserver.service;

import kr.ac.jejunu.giftrestserver.exception.GiftException;
import kr.ac.jejunu.giftrestserver.model.Game;
import kr.ac.jejunu.giftrestserver.model.PayInfo;
import kr.ac.jejunu.giftrestserver.payload.WithDrawPayLoad;
import kr.ac.jejunu.giftrestserver.repository.PayInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PayInfoService {
    private final GameService gameService;
    private final UserService userService;
    private final PayInfoRepository payInfoRepository;
    public void addTransaction(WithDrawPayLoad withDrawPayLoad) {
        PayInfo transaction = PayInfo.builder()
                .game(gameService.getGameFromId(withDrawPayLoad.getGameId()).get())
                .user(userService.getAccountFromUserSeqId(withDrawPayLoad.getUserSeqId()).get())
                .price(withDrawPayLoad.getPrice())
                .build();

        payInfoRepository.save(transaction);
    }

    public boolean hasTransaction(Long gameId) {
        return payInfoRepository.existsByGameId(gameId);
    }
}
