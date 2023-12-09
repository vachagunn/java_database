package org.example;


import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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

    public static List<Task> findAll(Connection connection) {
        List<Task> tasks = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM task");

            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getLong("id"));
                task.setTitle(resultSet.getString("title"));
                task.setDescription(resultSet.getString("description"));
                task.setTime(Instant.ofEpochMilli(resultSet.getLong("time")));
                task.getDifficult();
                task.setDone(resultSet.getInt("is_done") == 1);

                tasks.add(task);
            }

        } catch (SQLException ex) {
            System.out.println("sql ex");
            ex.printStackTrace();
        }
        return tasks;
    }

    public static void markDone(Connection connection, long id) {
        try {
            // Подготовленный запрос
            PreparedStatement preparedStatement = connection.prepareStatement("""
                        UPDATE task
                        SET is_done = 1
                        WHERE id = ?
                    """);

            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException ex) {
            System.out.println("sql ex");
            ex.printStackTrace();
        }
    }

    public static void edit(Connection connection, Task task) {
        try {
            // Подготовленный запрос
            PreparedStatement preparedStatement = connection.prepareStatement("""
                        UPDATE task
                        SET 
                            title = ?,
                            description = ?,
                            time = ?,
                            difficult = ?,
                            is_done = ?
                        WHERE id = ?
                    """);

            preparedStatement.setString(1, task.getTitle());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setLong(3, task.getTime().toEpochMilli());
            preparedStatement.setInt(4, task.getDifficult());
            preparedStatement.setInt(5, task.isDone() ? 1 : 0);
            preparedStatement.setLong(6, task.getId());

            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException ex) {
            System.out.println("sql ex");
            ex.printStackTrace();
        }
    }
}
