package org.example;

import java.sql.*;

import static org.example.DBUtils.addTask;

public class Main {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:sqlite:database.db";
        Connection connection = DriverManager.getConnection(url);

        addTask(connection, "Первая задача", "Подробное описание первой задачи", 1);
        addTask(connection, "Вторая задача", "Подробное описание второй задачи", 2);
        addTask(connection, "Третья задача", "Подробное описание третьей задачи", 3);

        connection.close();
    }
}