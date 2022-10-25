package com.kata.cinema.base.dao.impl;

import com.kata.cinema.base.dao.TopMovieDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

@Repository
public class TopMovieDaoImpl implements TopMovieDao {

    private final JdbcTemplate jdbcTemplate;

    public TopMovieDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void set(int scoreAmount, int limit) throws SQLException, IOException {

        jdbcTemplate.execute(Files.readString(Paths.get("src/main/resources/static/data/top_movies_set.sql")));

        Connection connection = Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection();

        CallableStatement callableStatement = connection.prepareCall("call top_movie_set(?,?)");
        callableStatement.setInt(1, scoreAmount);
        callableStatement.setInt(2, limit);
        callableStatement.execute();
    }
}
