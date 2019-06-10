package kr.ac.jejunu.giftrestserver.service;

import kr.ac.jejunu.giftrestserver.model.PayInfo;
import kr.ac.jejunu.giftrestserver.payload.WithDrawPayLoad;
import kr.ac.jejunu.giftrestserver.repository.PayInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PayInfoService {
    private final GameService gameService;
    private final UserService userService;
    private final PayInfoRepository payInfoRepository;
    public void addTransaction(WithDrawPayLoad withDrawPayLoad) {
        System.out.println(withDrawPayLoad.getGameId());
        PayInfo transaction = PayInfo.builder()
                .game(gameService.getGameFromId(withDrawPayLoad.getGameId()).get())
                .user(userService.getAccountFromUserSeqId(withDrawPayLoad.getUserSeqId()).get())
                .price(withDrawPayLoad.getPrice())
                .build();

        payInfoRepository.save(transaction);
    }
}
