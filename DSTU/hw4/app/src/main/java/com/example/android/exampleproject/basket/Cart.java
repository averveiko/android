package com.example.android.exampleproject.basket;

import com.example.android.exampleproject.domain.Book;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {
    private final List<Book> books = new ArrayList<>();

    private static final Cart instance = new Cart();

    public static Cart getInstance() {
        return instance;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public boolean removeBook(Book book) {
        return books.remove(book);
    }

    public int getBookCount() {
        return books.size();
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (Book book : books) {
            total = total.add(book.getPrice());
        }
        return total;
    }
}
