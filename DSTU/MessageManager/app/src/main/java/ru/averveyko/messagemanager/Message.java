package ru.averveyko.messagemanager;

public class Message {
    private String author;
    private String text;
    private int color;
    // TODO pic


    public Message(String author, String text, int color) {
        this.author = author;
        this.text = text;
        this.color = color;
    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
