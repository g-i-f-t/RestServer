package kr.ac.jejunu.giftrestserver.service;

import kr.ac.jejunu.giftrestserver.exception.GiftException;
import kr.ac.jejunu.giftrestserver.model.Game;
import kr.ac.jejunu.giftrestserver.model.PayInfo;
import kr.ac.jejunu.giftrestserver.payload.WithDrawPayLoad;
import kr.ac.jejunu.giftrestserver.repository.GameRepository;
import kr.ac.jejunu.giftrestserver.repository.PayInfoRepository;
import kr.ac.jejunu.giftrestserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PayInfoService {
    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final PayInfoRepository payInfoRepository;
    public void addTransaction(WithDrawPayLoad withDrawPayLoad) {
        PayInfo transaction = PayInfo.builder()
                .game(gameRepository.findById(withDrawPayLoad.getGameId()).get())
                .user(userRepository.findByuserSeqId(withDrawPayLoad.getUserSeqId()).get())
                .price(withDrawPayLoad.getPrice())
                .build();

        payInfoRepository.save(transaction);
    }

    public boolean hasTransaction(Long gameId) {
        return payInfoRepository.existsByGameId(gameId);
    }

    public List<PayInfo> findAllByAccountNum(Long userId) {
        return payInfoRepository.findAllByuserId(userId);
    }

    public Page<PayInfo> findLatestPayInfo() {
        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "payDate"));
        return payInfoRepository.findAll(pageRequest);
    }
}
