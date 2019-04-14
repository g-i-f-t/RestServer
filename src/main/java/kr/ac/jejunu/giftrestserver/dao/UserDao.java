package kr.ac.jejunu.giftrestserver.dao;

import kr.ac.jejunu.giftrestserver.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void createUser() {
        // Todo
    }

    /**
     * readUser
     * @param id 사용자 아이디
     * @param password  SHA-256 처리된 패스워드
     * @return 사용자 번호 반환
     */
    public Long readUser(String id, String password) {
        Long responseId = null;
        String sql = "SELECT * FROM user_info WHERE id = ? and password = ?";
        Object[] params = new Object[] { id, password };
        try {
            responseId = jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
                return rs.getLong("num");
            });
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }

        return responseId;
    }

    public void updateUser() {
        // Todo
    }

    public void deleteUser() {
        String sql = "DELETE FROM user_info WHERE id = ?";
        // Todo
    }
}
