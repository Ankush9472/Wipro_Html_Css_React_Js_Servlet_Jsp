package com.example.config;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.example.beans.Todo;

// We're implementing RowMapper to map rows of a ResultSet to Todo class objects
public class RowMapperImpl implements RowMapper<Todo> {

    // It converts database query results into Todo objects
    // mapRow extracts data from the ResultSet and creates a Todo object

    @Override
    public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Todo todo = new Todo();
        todo.setId(rs.getInt(1));           // column 1 = id
        todo.setTitle(rs.getString(2));     // column 2 = title
        todo.setDescription(rs.getString(3)); // column 3 = description
        return todo;
    }
}