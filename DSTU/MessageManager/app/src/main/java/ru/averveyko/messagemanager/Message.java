package ru.averveyko.messagemanager;

public class Message {
    private String author;
    private String text;
    // TODO pic


    public Message(String author, String text) {
        this.author = author;
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }
}
