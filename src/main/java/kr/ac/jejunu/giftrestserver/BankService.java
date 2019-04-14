package kr.ac.jejunu.giftrestserver;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class BankService {

    private static final String HOST = "https://testapi.open-pltform.or.kr";
    private static final String CLIENT_ID = "l7xx5772ad36de9c485b829b7069a676a48b";
    public String addUser() {
        final String url = "/oauth/2.0/authorize2";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Bearer " + CLIENT_ID);


        UriComponentsBuilder parameters = UriComponentsBuilder.fromHttpUrl(HOST + url)
            .queryParam("response_type", "code")
            .queryParam("client_id", CLIENT_ID)
            .queryParam("redirect_uri", "/")
            .queryParam("scope", "login")
            .queryParam("client_info", "test")
            .queryParam("auth_type", "0");

        HttpEntity<?> request = new HttpEntity<>(parameters, headers);

        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
//        HttpEntity<String> response = restTemplate.exchange(parameters.toUriString(), HttpMethod.GET, request, String.class);
        HttpEntity<String> response = restTemplate.exchange(parameters.toUriString(), HttpMethod.GET, request, String.class);
        return response.getBody();
    }
}
