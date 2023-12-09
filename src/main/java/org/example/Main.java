package org.example;

import java.sql.*;
import java.util.List;

import static org.example.DBUtils.addTask;
import static org.example.DBUtils.findAll;

public class Main {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:sqlite:database.db";
        Connection connection = DriverManager.getConnection(url);

        List<Task> tasks = findAll(connection);
        System.out.println(tasks.size());
        tasks.forEach(System.out::println);

        connection.close();
    }
}