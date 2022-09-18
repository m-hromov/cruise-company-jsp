package com.cruisecompany.db.dao.impl;

import com.cruisecompany.db.dao.AbstractDAO;
import com.cruisecompany.db.dao.UserAccountDAO;
import com.cruisecompany.db.entity.UserAccount;
import com.cruisecompany.db.dao.mapper.RowMapper;

public class UserAccountDAOImpl extends AbstractDAO<UserAccount> implements UserAccountDAO {
    public UserAccountDAOImpl(RowMapper<UserAccount> rowMapper, String table) {
        super(rowMapper, table);
    }

    @Override
    public long save(UserAccount obj) {
        return 0;
    }

    @Override
    public void update(UserAccount obj) {

    }
}
