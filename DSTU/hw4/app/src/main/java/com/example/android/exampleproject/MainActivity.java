package com.example.android.exampleproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.exampleproject.fragment.BookDetailFragment;
import com.example.android.exampleproject.fragment.CartFragment;
import com.example.android.exampleproject.fragment.OnFragmentItemClickListener;

public class MainActivity extends AppCompatActivity implements OnFragmentItemClickListener {

    private FrameLayout mContainer = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContainer = findViewById(R.id.book_detail_container);
        if (mContainer != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.book_detail_container,
                            BookDetailFragment.newInstance(0))
                    .addToBackStack(null)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
        }

        Button butShowCart = findViewById(R.id.but_show_cart);
        butShowCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mContainer != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.book_detail_container,
                                    CartFragment.newInstance())
                            .addToBackStack(null)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .commit();
                } else {
                    Intent intent = new Intent(MainActivity.this, CartActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public void onFragmentItemClick(int id) {
        if (mContainer != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.book_detail_container,
                            BookDetailFragment.newInstance(id))
                    .addToBackStack(null)
                    .setTransition(FragmentTransaction
                            .TRANSIT_FRAGMENT_FADE)
                    .commit();
        } else {
            Intent intent = new Intent(this,
                    BookDetailActivity.class);
            intent.putExtra(BookDetailActivity.BOOK_ID_KEY, id);
            startActivity(intent);
        }


    }
}