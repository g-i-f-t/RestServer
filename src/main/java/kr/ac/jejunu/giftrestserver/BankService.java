package kr.ac.jejunu.giftrestserver;

import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class BankService {

    private static final String HOST = "https://testapi.open-platform.or.kr";
    private static final String CLIENT_ID = "l7xx7ca57b2adb1340a5b5c982e87fbc44b1";

    public String addUser() {
        final String url = "/oauth/2.0/authorize2";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("Authorization", "Bearer " + CLIENT_ID);

        UriComponentsBuilder parameters = UriComponentsBuilder.fromHttpUrl(HOST + url)
            .queryParam("response_type", "code")
            .queryParam("client_id", CLIENT_ID)
            .queryParam("redirect_uri", "http://localhost:8080/auth")
            .queryParam("scope", "inquiry")
            .queryParam("client_info", "test")
            .queryParam("auth_type", "0");

        HttpEntity entity = new HttpEntity(headers);

        System.out.println(parameters.toUriString());
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        ResponseEntity<String> response = restTemplate.exchange(parameters.toUriString(), HttpMethod.GET, entity, String.class);
        return response.getBody();
    }
}
