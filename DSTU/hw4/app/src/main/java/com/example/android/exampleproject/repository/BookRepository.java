package com.example.android.exampleproject.repository;

import com.example.android.exampleproject.domain.Book;

import java.math.BigDecimal;
import java.util.ArrayList;

public class BookRepository {
    private static final BookRepository ourInstance = new BookRepository();

    public static BookRepository getInstance() {
        return ourInstance;
    }

    private BookRepository() {
    }

    private final ArrayList<Book> mBooks = new ArrayList<Book>() {{
        add(new Book("Коллекционер", "Роман рассказывает историю безумного клерка Фредерика Клегга, попытавшегося добавить в свою коллекцию живого человека.",
                BigDecimal.valueOf(500.50)));
        add(new Book("Идиот", "26-летний князь Мышкин возвращается из Швейцарии, где провёл несколько лет, лечась от недуга. Размышление Достоевского о добре и красоте в мире наживы, безбожия, разгула эгоистических страстей не оставят вас равнодушными.",
                BigDecimal.valueOf(700.0)));
        add(new Book("Пролетая над гнездом кукушки", "Огромный индеец по кличке Вождь Бромден притворяется глухонемым в психиатрической больнице. Приход нового пациента постепенно меняет его жизнь и побуждает на совершение побега.",
                BigDecimal.valueOf(300.50)));
    }};

    public Book getBook(int id) {
        return mBooks.get(id);
    }

    public ArrayList<Book> getBooks() {
        return mBooks;
    }
}

