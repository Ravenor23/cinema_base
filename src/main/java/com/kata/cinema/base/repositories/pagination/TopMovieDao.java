package com.kata.cinema.base.repositories.pagination;

import java.io.IOException;
import java.sql.SQLException;

public interface TopMovieDao {

    void set(int scoreAmount, int limit) throws IOException, SQLException;
}
