package edu.school21.chat.repositories;

import edu.school21.chat.models.Message;

import java.sql.SQLException;

public interface MessagesRepository {
    Message findById(Long id) throws SQLException;
}