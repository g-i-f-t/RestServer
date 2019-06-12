package kr.ac.jejunu.giftrestserver.service;

import kr.ac.jejunu.giftrestserver.exception.GiftException;
import kr.ac.jejunu.giftrestserver.model.Developer;
import kr.ac.jejunu.giftrestserver.model.User;
import kr.ac.jejunu.giftrestserver.payload.DeveloperPayload;
import kr.ac.jejunu.giftrestserver.payload.ValidatePayload;
import kr.ac.jejunu.giftrestserver.repository.DeveloperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeveloperService {

    private final BankService bankService;
    private final DeveloperRepository developerRepository;

    public Optional<Developer> getDeveloperByUserSeqId(String userSeqId) {
        return developerRepository.findByUserSeqId(userSeqId);
    }

    public Map<String, Object> addDeveloper(DeveloperPayload developerPayload) throws GiftException {
        Map<String, Object> tokenMap = bankService.getToken(developerPayload.getAuthCode());
        System.out.println(tokenMap.toString());
        final String accessToken = (String) tokenMap.get("access_token");
        final String refreshToken = (String) tokenMap.get("refresh_token");
        final String userSeqId = (String) tokenMap.get("user_seq_no");

        if(developerRepository.existsByUserSeqId(userSeqId))
            throw new GiftException("Account Already Exists", 400);

        try {
            Developer developer = Developer.builder()
                    .email(developerPayload.getEmail())
                    .name(developerPayload.getName())
                    .password(developerPayload.getPassword())
                    .scope(developerPayload.getScope())
                    .refreshToken(refreshToken)
                    .userSeqId(userSeqId)
                    .build();
            developerRepository.save(developer);
        } catch (Exception e) {
            throw new GiftException("couldn't add Account", 401);
        }

        return new HashMap<>() {{
            put("access_token", accessToken);
            put("user_seq_no", userSeqId);
        }};
    }

    public Map<String, Object> authDeveloper(ValidatePayload account) throws GiftException {
        Optional<Developer> developerOptional = developerRepository.findByEmailAndPassword(account.getEmail(), account.getPassword());

        if(developerOptional.isEmpty()) throw new GiftException("couldn't find account", 400);

        Developer developer = developerOptional.get();
        String accessToken = refreshDeveloperToken(developer);

        return new HashMap<>() {{
            put("access_token", accessToken);
            put("user_seq_no", developer.getUserSeqId());
        }};
    }

    private String refreshDeveloperToken(Developer developer) {
        Map<String, Object> response = bankService.updateToken(developer.getRefreshToken(), developer.getScope());
        System.out.println(response.toString());
        String accessToken = (String) response.get("access_token");
        String refreshToken = (String) response.get("refresh_token");
        developer.setRefreshToken(refreshToken);
        developerRepository.save(developer);
        return accessToken;
    }
}
