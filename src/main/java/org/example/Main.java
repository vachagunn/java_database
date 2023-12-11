package org.example;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.sql.*;
import java.time.Instant;
import java.util.List;

import static org.example.DBUtils.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:sqlite:database.db";
        Connection connection = DriverManager.getConnection(url);
        List<Task> all = findAll(connection);
        connection.close();

        JFrame frame = new JFrame();
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Todo DB");

        JTable table = new JTable(new TableModel() {
            @Override
            public int getRowCount() {
                return all.size();
            }

            @Override
            public int getColumnCount() {
                return 6;
            }

            @Override
            public String getColumnName(int columnIndex) {
                return (new String[] {
                    "id", "Название", "Описание", "Срок", "Сложность", "Статус"
                })[columnIndex];
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return (new Class[] {
                        Long.class, String.class, String.class, Instant.class, Integer.class, Boolean.class
                })[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                Task task = all.get(rowIndex);
                switch (columnIndex) {
                    case 0: return task.getId();
                    case 1: return task.getTitle();
                    case 2: return task.getDescription();
                    case 3: return task.getTime();
                    case 4: return task.getDifficult();
                    case 5: return task.isDone();
                }
                return "";
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

            }

            @Override
            public void addTableModelListener(TableModelListener l) {

            }

            @Override
            public void removeTableModelListener(TableModelListener l) {

            }
        });
        JPanel panel = new JPanel();
        JButton addTask = new JButton("Добавить");

        panel.add(addTask);
        frame.add(panel, BorderLayout.NORTH);
        frame.add(table);
        frame.pack();
    }
}