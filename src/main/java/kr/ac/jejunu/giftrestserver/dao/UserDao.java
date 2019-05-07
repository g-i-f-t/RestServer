package kr.ac.jejunu.giftrestserver.dao;

import kr.ac.jejunu.giftrestserver.vo.Profile;
import kr.ac.jejunu.giftrestserver.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int createUser(User user) {
        // Todo
        String sql = "INSERT INTO user_info (password, name, email, scope, refresh_token, user_seq_id) VALUES (?, ?, ?, ?, ?, ?)";
        Object[] params = new Object[]
                {
                user.getPassword(),
                user.getName(),
                user.getEmail(),
                user.getScope(),
                user.getRefreshToken(),
                user.getUserSeqId()
        };

        return jdbcTemplate.update(sql, params);
    }

    /**
     * readUser
     * @param id 사용자 아이디
     * @param password  SHA-256 처리된 패스워드
     * @return 사용자 번호 반환
     */
    public Long authUser(String id, String password) {
        Long responseId = null;
        String sql = "SELECT * FROM user_info WHERE id = ? and password = ?";
        Object[] params = new Object[] { id, password };
        try {
            responseId = jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> rs.getLong("num"));
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

    public Profile getAccountFromUserSeqId(String userSeqId) {
        String sql = "SELECT name, email from user_info where user_seq_id = ?";
        Object[] params = new Object[] {userSeqId};
        Profile profile = null;
        try {
            profile = jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> new Profile() {{
                setName(rs.getString("name"));
                setEmail(rs.getString("email"));
            }});
        } catch (EmptyResultDataAccessException e) { e.printStackTrace(); }
        System.out.println(profile.getName());
        return profile;
    }
}
