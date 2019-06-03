package kr.ac.jejunu.giftrestserver.repository;

import kr.ac.jejunu.giftrestserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
