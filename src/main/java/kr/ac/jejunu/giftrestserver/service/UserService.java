package kr.ac.jejunu.giftrestserver.service;

import kr.ac.jejunu.giftrestserver.model.User;
import kr.ac.jejunu.giftrestserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void addUser(User user) {
        userRepository.save(user);
    }
}
