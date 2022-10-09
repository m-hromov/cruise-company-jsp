package com.cruisecompany.db.dao;

import com.cruisecompany.db.DBProvider;
import com.cruisecompany.db.dao.mapper.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SimpleQueryExecutor<T> {
    private final RowMapper<T> rowMapper;

    protected SimpleQueryExecutor(RowMapper<T> rowMapper) {
        this.rowMapper = rowMapper;
    }

    protected List<T> executeQuery(String query, Object... params) {
        List<T> list = new ArrayList<>();
        try (Connection connection = DBProvider.getInstance().getConnection();
             PreparedStatement ps = preparedStatement(query, connection, params);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(rowMapper.map(rs));
            }
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    protected Optional<T> executeSingleGetQuery(String query, Object... params) {
        try (Connection connection = DBProvider.getInstance().getConnection();
             PreparedStatement ps = preparedStatement(query, connection, params);
             ResultSet rs = ps.executeQuery()) {

            if(rs.next())
                return Optional.ofNullable(rowMapper.map(rs));
            return Optional.empty();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    protected long executeInsert(String query, Object... params) {
        try (Connection connection = DBProvider.getInstance().getConnection();
             PreparedStatement ps = preparedStatement(query, connection, params)) {
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();

            if (!generatedKeys.next()) throw new SQLException("0 rows affected");
            return generatedKeys.getLong(1);

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    protected void executeUpdate(String query, Object... params) {
        try (Connection connection = DBProvider.getInstance().getConnection();
             PreparedStatement ps = preparedStatement(query, connection, params)) {
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    protected long executeSingleGetLongQuery(String query, Object... params) {
        try (Connection connection = DBProvider.getInstance().getConnection();
             PreparedStatement ps = preparedStatement(query, connection, params)) {
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) throw new SQLException("0 rows affected");
            return rs.getLong(1);

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private PreparedStatement preparedStatement(String query, Connection connection, Object... params) {
        try {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            for (int i = 1; i <= params.length; i++) {
                ps.setObject(i, params[i - 1]);
            }
            return ps;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
