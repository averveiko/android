package com.example.android.exampleproject.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Book {
    private final String name;
    private final String description;
    private final BigDecimal price;

    public Book(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return name.equals(book.name) &&
                description.equals(book.description) &&
                price.equals(book.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, price);
    }
}

