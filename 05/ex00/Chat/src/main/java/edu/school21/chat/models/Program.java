package edu.school21.chat.models;

import java.sql.*;
import java.util.*;

public class Program {
    private static final String DB_URL = "jdbc:postgresql://localhost:6666/postgres";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "12345678";

    public static void main(String[] args) throws SQLException {
        System.out.println("Hello world");

        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Scanner scanner = new Scanner(System.in);
        String login;
        String password;
        String SQL_SHOW_USERS = "select * from chat.users order by id";
        String SQL_ADD_USER = "insert into chat.users (login, passwd) values (?, ?)";
        Statement statement = connection.createStatement();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_USER);
        System.out.println("Введите имя пользователя: ");
        login = scanner.nextLine();
        System.out.println("Введите пароль для пользователя " + login + " : ");
        password = scanner.nextLine();
        preparedStatement.setString(1, login);
        preparedStatement.setString(2, password);
        preparedStatement.executeUpdate();
        ResultSet result = statement.executeQuery(SQL_SHOW_USERS);
        System.out.println(result);
        while(result.next()) {
            System.out.println(result.getString(3));
        }
        User user01 = new User(7, "user-1", "1234",null,null);
        User user02 = new User(7, "user-1", "1234",null,null);
        System.out.println("_____");
       System.out.println(user01.getLogin());
        System.out.println("_____");
        System.out.println(user01.getCreatedChatrooms());
        System.out.println(user02.equals(user02));
    }
}
