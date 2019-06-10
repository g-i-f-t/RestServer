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
}
