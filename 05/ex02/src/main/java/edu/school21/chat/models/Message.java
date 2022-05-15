package edu.school21.chat.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    public LocalDateTime dateTime;
    public Long id;
    public User author;
    public Chatroom room;
    public String text;
    private LocalDateTime date;

    public Message(Long id, User author, Chatroom room, String text, LocalDateTime date) {
        this.id = id;
        this.author = author;
        this.room = room;
        this.text = text;
        this.date = date;
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object e) {
        return super.equals(e);
    }

    @Override
    public String toString() {
        return String.format("{ Message: id=%d; authorID=%s; roomID=%s; text=%s; dateTime=%s; }",
                id,
                author == null ? null : author.getId(),
                room == null ? null : room.getId(),
                text,
                dateTime);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Chatroom getRoom() {
        return room;
    }

    public void setRoom(Chatroom room) {
        this.room = room;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

}