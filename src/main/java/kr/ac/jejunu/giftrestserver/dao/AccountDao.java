package kr.ac.jejunu.giftrestserver.dao;

import kr.ac.jejunu.giftrestserver.vo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addAccount(Account account) {
        String sql = "INSERT INTO account_info (userId, scope, code) VALUES (?, ?, ?)";
        Object[] params = new Object[] { account.getClientInfo(), account.getScope(), account.getCode() };
        return jdbcTemplate.update(sql, params);
    }
}
