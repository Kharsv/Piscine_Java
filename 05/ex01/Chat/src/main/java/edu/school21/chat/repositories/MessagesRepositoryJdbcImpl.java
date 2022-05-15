package edu.school21.chat.repositories;


import edu.school21.chat.models.Message;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Message findById(Long id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(String.format("select * from chat.msgs where id=%d", id));

            if (result.next()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                return new Message(id,
                        null,
                        null,
                        result.getString(4),
                        LocalDateTime.parse(result.getString(5), formatter));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    private final Connection connection;
}
