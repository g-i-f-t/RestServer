package kr.ac.jejunu.giftrestserver;

import kr.ac.jejunu.giftrestserver.dao.GameDao;
import kr.ac.jejunu.giftrestserver.vo.Game;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

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
    GameDao gameDao;

    @Test
    public void exampleTest() throws Exception {
        this.mvc.perform(get("/test"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, World!"));

    }

    @Test
    public void getGame() throws Exception {
        this.mvc.perform(get("/game/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        "{\"code\":200,\"data\":{\"gameId\":1,\"name\":\"silhwa\",\"developer\":\"aerain\",\"category\":\"fps\",\"currentPrice\":0,\"goalPrice\":0,\"success\":false},\"messages\":\"success\"}"
                ));
    }

    @Test
    public void insertGame() throws Exception {
        Game game = new Game();
        game.setName("tee");
        game.setDeveloper("tes");
        game.setCategory("fps");
        game.setGoalPrice(50000000);


        this.mvc.perform(put("/game/insert")
                        .contentType("application/json")
                        .characterEncoding("UTF-8").content("{\"name\":\"tee\",\"developer\":\"tes\",\"category\":\"fps\",\"goalPrice\":5000000}"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"code\":200,\"message\":\"Successfully added\"}"));
    }
}
