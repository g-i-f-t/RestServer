package kr.ac.jejunu.giftrestserver.repository;

import kr.ac.jejunu.giftrestserver.model.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {
    Optional<Developer> findByUserSeqId(String userSeqId);

    boolean existsByUserSeqId(String userSeqId);

    Optional<Developer> findByEmailAndPassword(String email, String password);
}
