package edu.school21.chat.repositories;

import edu.school21.chat.models.Message;

public interface MessagesRepository {

    Message findById(Long id);

    void save(Message message);

}