package kr.ac.jejunu.giftrestserver.repository;

import kr.ac.jejunu.giftrestserver.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {

}
