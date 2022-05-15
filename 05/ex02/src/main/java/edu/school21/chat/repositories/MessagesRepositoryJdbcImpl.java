package edu.school21.chat.repositories;

import edu.school21.chat.models.Message;
import java.time.LocalDateTime;
import javax.sql.DataSource;
import java.sql.*;

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
                return new Message(id,
                        null,
                        null,
                        result.getString(4),
                        result.getTimestamp(5) == null ? null : result.getTimestamp(5).toLocalDateTime());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public void save(Message message) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into chat.msgs (room_id, sender, message, time) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setLong(1, message.getRoom().getId());
            preparedStatement.setLong(2, message.getAuthor().getId());
            preparedStatement.setString(3, message.getText());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(message.getDateTime()));

            try {

                preparedStatement.execute();
                ResultSet result = preparedStatement.getGeneratedKeys();
                if (result.next()) {
                    message.setId(result.getLong(1));
                }

            } catch (SQLException e) {
                throw new NotSavedSubEntityException();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static class NotSavedSubEntityException extends RuntimeException {
        public NotSavedSubEntityException() {
            super("Sub entities doesn't exists");
        }
    }

    private final Connection connection;
}
