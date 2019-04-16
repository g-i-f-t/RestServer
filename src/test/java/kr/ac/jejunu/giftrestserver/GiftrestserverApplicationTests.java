package kr.ac.jejunu.giftrestserver;

import kr.ac.jejunu.giftrestserver.vo.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GiftrestserverApplicationTests {

    @Autowired
    BankService bankService;

    @Test
    public void contextLoads() {
        System.out.println("Hi!");
    }

    @Test
    public void bankTest() {
        BankService test = new BankService();
//        String t = test.addUser();
//        System.out.println(t);
    }

    @Test
    public void inquiryTest() {
        Account account = new Account();
        account.setCode("2b82b2df-4800-4297-b1fe-352f2d8ca28a");
        account.setScope("login inquiry transfer");
        account.setClientInfo("test");

        BankService test = new BankService();
        Map<String, Object> res = test.getToken(account.getCode());
        System.out.println(res.toString());
        // {access_token=a46db938-aa13-4266-b11c-58c19d153063, token_type=Bearer, expires_in=7776000, refresh_token=d51cdf12-a7fb-4838-860d-e643e77a0134, scope=login inquiry transfer, user_seq_no=1100034971}
        // Todo 처음 인증 코드받으면 가입할때 client_info(아이디)에 적힌 튜플에 refresh_token과 user_seq_no 삽입.
    }

    @Test
    public void updateToken() {
        final String access_token = "10a896df-6f08-4f9f-9f27-1f5a6911b26b";
        final String token_type = "Bearer";
        final String scope = "login inquiry transfer";
        final String refresh_token = "77461696-49c7-4191-b05d-cbf5f9ade144";
        final String user_seq_no = "1100034971";

        // Todo Token VO 만들어라
        final Map<String, Object> newAccessToken = bankService.updateToken(refresh_token, scope);

        System.out.println(newAccessToken.toString());
    }

    @Test
    public void userLookup() {
        final String user_seq_no = "1100034971";
        final String access_token = "10a896df-6f08-4f9f-9f27-1f5a6911b26b";
        // Todo VO 만들어라
        final Map<String, Object> user = bankService.userLookup(access_token, user_seq_no);
        //  {api_tran_id=85f69aa3-ffca-4c4d-9066-7d69ca625f69, rsp_code=A0000, rsp_message=, api_tran_dtm=20190416214022937, user_seq_no=1100034971, user_ci=5CmYqbBQ8A0EAdGz/U/IoJe/N3LNi9Pvs7WmvhCqzK3Lxd/Xoe9BGYGHJiTTbMoyh664XvWYklcHw/Yduu10+A==, user_name=이청길, res_cnt=1, res_list=[{fintech_use_num=199004418057725343074823, account_alias=내거, bank_code_std=004, bank_code_sub=0000000, bank_name=KB국민은행, account_num_masked=70180204203***, account_holder_name=이청길, account_type=P, inquiry_agree_yn=Y, inquiry_agree_dtime=20190416204856, transfer_agree_yn=Y, transfer_agree_dtime=20190416204856}]}
        System.out.println(user.toString());
    }

    @Test
    public void accountLookup() {
        final String access_token = "10a896df-6f08-4f9f-9f27-1f5a6911b26b";
        final String user_seq_no = "1100034971";

        // Todo VO list 만들어라
        final Map<String, Object> account = bankService.accountLookup(access_token, user_seq_no);
        List<Map<String, Object>> list = (List<Map<String, Object>>) account.get("res_list");
        System.out.println(list.get(0).get("fintech_use_num"));
    }

    @Test
    public void withDraw() {
        final String access_token = "10a896df-6f08-4f9f-9f27-1f5a6911b26b";
        final String fintech_use_num = "199004418057725343074823";
        final String log = "why";
        final String amount = "1";
        final Map<String, Object> transaction = bankService.withDraw(log, amount, access_token, fintech_use_num);
        System.out.println(transaction.toString());
    }
}
