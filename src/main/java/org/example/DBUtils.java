package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {
    public static void initTable(Connection connection) {
        // table task
        // id, title, description, time, difficult, isDone
        try {
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
        } catch (SQLException ex) {
            System.out.println("sql ex");
            ex.printStackTrace();
        }
    }

    public static void addTask(Connection connection, String title, String description, int difficult) {
        try {
            // Подготовленный запрос
            PreparedStatement preparedStatement = connection.prepareStatement("""
                    INSERT INTO task (title, description, time, difficult, is_done)
                    VALUES (?, ?, ?, ?, 0)
                """);

            preparedStatement.setString(1, title);
            preparedStatement.setString(2, description);
            preparedStatement.setLong(3, System.currentTimeMillis());
            preparedStatement.setInt(4, difficult);
            preparedStatement.execute();

            preparedStatement.close();

        } catch (SQLException ex) {
            System.out.println("sql ex");
            ex.printStackTrace();
        }

    }
}
