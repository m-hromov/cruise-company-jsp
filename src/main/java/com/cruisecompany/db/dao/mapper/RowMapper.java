package com.cruisecompany.db.dao.mapper;

import java.sql.ResultSet;

public interface RowMapper<T> {
    public T map(ResultSet rs);
}
