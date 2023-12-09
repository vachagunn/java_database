package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:sqlite:database.db";
        Connection connection = DriverManager.getConnection(url);

        // table task
        // id, title, description, time, difficult, isDone
        Statement statement = connection.createStatement();
        statement.execute("""
                CREATE TABLE task (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    title TEXT,
                    description TEXT,
                    time INTEGER,
                    difficult INTEGER,
                    is_done INTEGER
                )
                """);

        statement.close();
        connection.close();
    }
}