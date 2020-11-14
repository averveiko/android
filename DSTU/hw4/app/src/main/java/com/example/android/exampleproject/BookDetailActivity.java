package com.example.android.exampleproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.android.exampleproject.R;
import com.example.android.exampleproject.fragment.BookDetailFragment;

public class BookDetailActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "BookId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        final int bookId = getIntent().getIntExtra(BOOK_ID_KEY, -1);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.activity_book_detail,
                        BookDetailFragment.newInstance(bookId))
                .commit();

    }
}