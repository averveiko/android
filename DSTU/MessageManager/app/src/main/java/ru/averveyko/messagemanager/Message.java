package ru.averveyko.messagemanager;

import android.graphics.drawable.Drawable;

public class Message {
    private String author;
    private String text;
    private Drawable avatar;
    private int color;

    public Message(String author, String text, Drawable avatar, int color) {
        this.author = author;
        this.text = text;
        this.avatar = avatar;
        this.color = color;
    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public Drawable getAvatar() {
        return avatar;
    }

    public void setAvatar(Drawable avatar) {
        this.avatar = avatar;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
