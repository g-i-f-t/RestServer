package kr.ac.jejunu.giftrestserver.dao;

import kr.ac.jejunu.giftrestserver.vo.GameVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GiftDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Map<String, Object> getTest() {
        String sql = "SELECT * FROM test";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("messages",  "success");
        result.put("data", rows);

        return result;
    }



    public int insertGame(GameVO gameVO) {
        String sql = "insert into game_info (name, developer, category, current_price, goal_price, success) values (?, ?, ?, ?, ?, ?)";
        int id = jdbcTemplate.update(sql, gameVO.getName(), gameVO.getCategory(), gameVO.getCurrentPrice(), gameVO.getGoalPrice(), gameVO.isSuccess());
        return id;
    }

    public GameVO getGameFromId(int id) {

        return null;
    }
}
