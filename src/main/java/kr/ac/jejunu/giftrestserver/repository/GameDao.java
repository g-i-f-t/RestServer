package kr.ac.jejunu.giftrestserver.repository;

import kr.ac.jejunu.giftrestserver.model.Game;
import kr.ac.jejunu.giftrestserver.model.GameMinify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GameDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Map<String, Object> getTest() {
        String sql = "SELECT * FROM test";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("messages", "success");
        result.put("data", rows);

        return result;
    }

    public int insertGame(Game game) {
        String sql = "insert into game_info (name, developer, category, current_price, goal_price, game_information, investigation_information, investigation_condition, company_introduction) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] params = new Object[] {
                game.getName(),
                game.getDeveloper(),
                game.getCategory(),
                game.getCurrentPrice(),
                game.getGoalPrice(),
                game.getGameInformation(),
                game.getInvestigationInformation(),
                game.getInvestigationCondition(),
                game.getCompanyIntroduction() };

        return jdbcTemplate.update(sql, params);
    }

    public Collection<GameMinify> getGameCollection() {
        String sql = "SELECT game_id, name, developer, category, Success, current_price, goal_price FROM game_info";
        Collection<GameMinify> gameMinifyCollection = null;
        try {
            jdbcTemplate.query(sql, (rs, rowNum) -> {
               GameMinify game = new GameMinify();
               game.setGameId(rs.getInt("game_id"));
               game.setName(rs.getString("name"));
               game.setDeveloper(rs.getString("developer"));
               game.setCategory(rs.getString("category"));
               game.setCurrentPrice(rs.getInt("current_price"));
               game.setGoalPrice(rs.getInt("goal_price"));
               return game;
           });
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return gameMinifyCollection;
    }

    public Game getGameFromId(Long id) {
        String sql = "SELECT * FROM game_info WHERE game_id=?";
        Object[] params = new Object[] { id };
        Game result = null;
        try {
            result = jdbcTemplate.queryForObject(sql, params, /* RowMapper*/ (rs, rowNum) -> {
                Game game = new Game();
                game.setGameId(rs.getInt("game_id"));
                game.setName(rs.getString("name"));
                game.setDeveloper(rs.getString("developer"));
                game.setCategory(rs.getString("category"));
                game.setSuccess(rs.getBoolean("success"));
                game.setCurrentPrice(rs.getInt("current_price"));
                game.setGoalPrice(rs.getInt("goal_price"));
                game.setGameInformation(rs.getString("game_information"));
                game.setInvestigationInformation(rs.getString("investigation_information"));
                game.setInvestigationCondition(rs.getString("investigation_condition"));
                game.setCompanyIntroduction(rs.getString("company_introduction"));

                return game;
            });
        } catch (EmptyResultDataAccessException e) { e.printStackTrace(); }
        return result;
    }
}
