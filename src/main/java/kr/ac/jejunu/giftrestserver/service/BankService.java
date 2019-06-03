package kr.ac.jejunu.giftrestserver.service;

import kr.ac.jejunu.giftrestserver.ClientKey;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BankService {

    private static final String HOST = "https://testapi.open-platform.or.kr";
    private final ClientKey clientKey;

    public Map<String, Object> updateToken(String refresh_token, String scope) {
        final String uri = "/oauth/2.0/token";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");
        headers.add("charset", "UTF-8");
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("client_id", clientKey.getCLIENT_ID());
        parameters.add("client_secret", clientKey.getCLIENT_SECRET());
        parameters.add("refresh_token", refresh_token);
        parameters.add("scope", scope);
        parameters.add("grant_type", "refresh_token");

        return HttpConnectionService.post(HOST + uri, headers, parameters);
    }

    public Map<String, Object> userLookup(String access_token, String user_seq_no) {
        final String url = "/user/me";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + access_token);

        UriComponentsBuilder parameters = UriComponentsBuilder.fromHttpUrl(HOST + url)
                .queryParam("user_seq_no", user_seq_no);
        return HttpConnectionService.get(headers, parameters);
    }

    public Map<String, Object> accountLookup(String access_token, String user_seq_no) {
        final String url = "/account/list";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + access_token);

        UriComponentsBuilder parameters = UriComponentsBuilder.fromHttpUrl(HOST + url)
                .queryParam("user_seq_no", user_seq_no)
                .queryParam("include_cancel_yn", "Y")
                .queryParam("sort_order", "A");

        return HttpConnectionService.get(headers, parameters);
    }

    public Map<String, Object> addUser() {
        final String url = "/oauth/2.0/authorize2";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json");

        UriComponentsBuilder parameters = UriComponentsBuilder.fromHttpUrl(HOST + url)
            .queryParam("response_type", "code")
            .queryParam("client_id", clientKey.getCLIENT_ID())
            .queryParam("redirect_uri", clientKey.getREDIRECT_URI())
            .queryParam("scope", "inquiry")
            .queryParam("client_info", "test")
            .queryParam("auth_type", "0");

        return HttpConnectionService.get(headers, parameters);
    }

    public Map<String, Object> getToken(String code) {
        final String uri = "/oauth/2.0/token";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("charset", "UTF-8");
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("code", code);
        parameters.add("client_id", clientKey.getCLIENT_ID());
        parameters.add("client_secret", clientKey.getCLIENT_SECRET());
        parameters.add("redirect_uri", clientKey.getREDIRECT_URI());
        parameters.add("grant_type", "authorization_code");

        return HttpConnectionService.post(HOST + uri, headers, parameters);
    }

    public Map<String, Object> withDraw(String content, String amount, String access_token, String fintech_use_num) {
        final String url = "/v1.0/transfer/withdraw";

        SimpleDateFormat dFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);
        Date currentTime = new Date();
        String dTime = dFormat.format(currentTime);

        System.out.println(dTime);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Authorization", "Bearer " + access_token);

        Map<String, String> parameters = new HashMap<>();
        parameters.put("dps_print_content", content);
        parameters.put("fintech_use_num", fintech_use_num);
        parameters.put("tran_amt", amount);
        parameters.put("tran_dtime", dTime);

        return HttpConnectionService.postJson(HOST + url, headers, parameters);
    }


}
