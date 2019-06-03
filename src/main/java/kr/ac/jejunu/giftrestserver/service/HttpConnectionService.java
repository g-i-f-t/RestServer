package kr.ac.jejunu.giftrestserver.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

public class HttpConnectionService {
    public static Map<String, Object> post(String uri, HttpHeaders headers, MultiValueMap<String, String> parameters) {
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(parameters, headers);
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        String result = restTemplate.postForObject(uri, request, String.class);


        Map<String, Object> map = null;

        return JSONParserService.parse(result);
    }

    public static Map<String, Object> get(HttpHeaders headers, UriComponentsBuilder parameters) {
        HttpEntity entity = new HttpEntity(headers);

        System.out.println(parameters.toUriString());
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        ResponseEntity<String> response = restTemplate.exchange(parameters.toUriString(), HttpMethod.GET, entity, String.class);

        Map<String, Object> map = null;

        return JSONParserService.parse(response.getBody());
    }

    public static Map<String, Object> postJson(String url, HttpHeaders headers, Map<String, String> parameters) {
        String json = JSONParserService.stringify(parameters);
        System.out.println(json);
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

        HttpEntity<String> request = new HttpEntity<String>(json, headers);
        String answer = restTemplate.postForObject(url, request, String.class);

        return JSONParserService.parse(answer);
    }
}