package org.example;

import java.sql.*;
import java.time.Instant;
import java.util.List;

import static org.example.DBUtils.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:sqlite:database.db";
        Connection connection = DriverManager.getConnection(url);

        // todo filter undone

        findAllUndone(connection).forEach(System.out::println);

        connection.close();
    }
}