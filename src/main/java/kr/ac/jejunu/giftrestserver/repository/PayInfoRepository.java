package kr.ac.jejunu.giftrestserver.repository;

import kr.ac.jejunu.giftrestserver.model.PayInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PayInfoRepository extends JpaRepository<PayInfo, Long> {
    Optional<PayInfo> findByGameId(Long gameId);

    boolean existsByGameId(Long gameId);
}
