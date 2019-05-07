package kr.ac.jejunu.giftrestserver;

import kr.ac.jejunu.giftrestserver.dao.UserDao;
import kr.ac.jejunu.giftrestserver.vo.Profile;
import kr.ac.jejunu.giftrestserver.vo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    UserDao userdao;

    @Test
    public void userSeqIdTest() {
        String email = "j40f893@gmail.com";
        String name = "이청길";
        String userSeqId = "1100034971";
        Profile profile = userdao.getAccountFromUserSeqId(userSeqId);

        assertThat(profile.getEmail(), is(email));
        assertThat(profile.getName(), is(name));
    }
}
