package kr.ac.jejunu.giftrestserver.dao;

import kr.ac.jejunu.giftrestserver.vo.Profile;
import kr.ac.jejunu.giftrestserver.vo.RefreshVO;
import kr.ac.jejunu.giftrestserver.vo.User;
import kr.ac.jejunu.giftrestserver.vo.ValidateVO;
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
     *
     * @param validateVO
     * @return
     */
    public RefreshVO authUser(ValidateVO validateVO) {
        RefreshVO refreshVO= null;
        String sql = "SELECT refresh_token, scope, user_seq_id FROM user_info WHERE email = ? and password = ?";
        Object[] params = new Object[] { validateVO.getEmail(), validateVO.getPassword() };
        try {
            refreshVO = jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> new RefreshVO() {{
                setRefreshToken(rs.getString("refresh_token"));
                setScope(rs.getString("scope"));
                setUserSeqNo(rs.getString("user_seq_id"));
            }});
        } catch (EmptyResultDataAccessException e) {
        }

        return refreshVO;
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

    public void updateUserForTokenUpdate(String refreshToken, String userSeqNo) {
        String sql = "UPDATE user_info SET refresh_token=? WHERE user_seq_id=?";
        Object[] params = new Object[] { refreshToken, userSeqNo };
        jdbcTemplate.update(sql, params);
    }
}
