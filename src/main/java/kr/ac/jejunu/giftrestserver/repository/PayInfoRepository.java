package kr.ac.jejunu.giftrestserver.repository;

import kr.ac.jejunu.giftrestserver.model.Game;
import kr.ac.jejunu.giftrestserver.model.PayInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PayInfoRepository extends JpaRepository<PayInfo, Long> {
    Optional<PayInfo> findByGameId(Long gameId);

    boolean existsByGameId(Long gameId);

    List<PayInfo> findAllByuserId( Long userId);
}
