package kr.ac.jejunu.giftrestserver;

import kr.ac.jejunu.giftrestserver.dao.GiftDAO;
import kr.ac.jejunu.giftrestserver.vo.GameVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DaoTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    GiftDAO giftDAO;

    @Test
    public void exampleTest() throws Exception {
        this.mvc.perform(get("/test"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, World!"));

    }

    @Test
    public void getGameList() throws Exception {
//
//        gameVO.setCategory("FPS");
//        gameVO.setName("이거 가능?");
//        gameVO.setDeveloper("Aerain");
//        gameVO.setSuccess(false);
//        gameVO.setCurrentPrice(0);
//        gameVO.setGoalPrice(1000000);
//
//        int id = testDAO.insertGame(gameVO);
//
//        GameVO testGameVO = testDAO.getGameFromId(id);
//        if(testGameVO.getGameId() != id) throw

        this.mvc.perform(get("/game"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello!"))
    }
}
