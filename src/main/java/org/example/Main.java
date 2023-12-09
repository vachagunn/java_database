package org.example;

import java.sql.*;
import java.time.Instant;
import java.util.List;

import static org.example.DBUtils.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:sqlite:database.db";
        Connection connection = DriverManager.getConnection(url);


        // todo edit
        // todo filter undone

        Task task = new Task();
        task.setId(3);
        task.setTitle("Третья задача");
        task.setDescription("Измененное описание третьей задачи");
        task.setTime(Instant.ofEpochMilli(System.currentTimeMillis() + 7 * 24 * 60 * 60* 1000));
        task.setDifficult(4);
        task.setDone(false);

        edit(connection, task);
        findAll(connection).forEach(System.out::println);

        connection.close();
    }
}