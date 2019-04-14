package kr.ac.jejunu.giftrestserver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GiftrestserverApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println("Hi!");
    }

    @Test
    public void bankTest() {
        BankService test = new BankService();
        String t = test.addUser();
        System.out.println(t);
    }

}
