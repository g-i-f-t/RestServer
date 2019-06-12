package kr.ac.jejunu.giftrestserver.repository;

import kr.ac.jejunu.giftrestserver.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {
    @Query(value= "SELECT g.category FROM Game g GROUP BY g.category")
    List<String> findAllCategory();
    Collection<Game> findAllBySuccess(boolean b);
    List<Game> findAllByCategory(String category);

    @Query(value ="UPDATE Game g SET g.currentPrice = current_price")
    void incrementPrice(Long price, Long gameId);

    @Query(value = "SELECT * FROM game_info ORDER BY current_price / goal_price DESC LIMIT 1", nativeQuery = true)
    Optional<Game> findHotGame();

    @Query("SELECT g FROM Game g WHERE g.developer.userSeqId = :userSeqId")
    List<Game> findAllByDeveloperUserSeqId(@Param("userSeqId") String userSeqId);
}
