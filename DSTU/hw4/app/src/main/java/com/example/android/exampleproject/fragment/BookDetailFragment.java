package com.example.android.exampleproject.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.exampleproject.R;
import com.example.android.exampleproject.basket.Cart;
import com.example.android.exampleproject.domain.Book;
import com.example.android.exampleproject.repository.BookRepository;

public class BookDetailFragment extends Fragment {

    private static final String CART_TOTAL_MSG = "Всего %s книг на общую сумму %s рублей.";
    private int mBookId = 0;
    private static final String BOOK_ID_KEY = "BookId";
    private final BookRepository mBooksRepository = BookRepository.getInstance();
    private final Cart cart = Cart.getInstance();

    public static BookDetailFragment newInstance(int bookId) {
        BookDetailFragment fragment = new BookDetailFragment();
        Bundle args = new Bundle();
        args.putInt(BOOK_ID_KEY, bookId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mBookId = getArguments().getInt(BOOK_ID_KEY, 0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_book_detail, container, false);
        Book book = mBooksRepository.getBook(mBookId);
        TextView nameTextView = view.findViewById(R.id.name_text_view);
        nameTextView.setText(book.getName());
        TextView descriptionTextView = view.findViewById(R.id.description_text_view);
        descriptionTextView.setText(book.getDescription());
        TextView priceTextView = view.findViewById(R.id.price_text_view);
        priceTextView.setText(book.getPrice().toString() + " руб.");

        // Cart
        Button butAddToCart = view.findViewById(R.id.but_add_to_cart);
        Button butRemoveFromCart = view.findViewById(R.id.but_remove_from_cart);

        butAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Добавляем книгу в корзину
                cart.addBook(mBooksRepository.getBook(mBookId));
                // Показываем информацию о корзине
                cartAlertDialog(view.getContext(), "Успешно добавлено в корзину. " + CART_TOTAL_MSG);
            }
        });

        butRemoveFromCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Добавляем книгу в корзину
                if (cart.removeBook(mBooksRepository.getBook(mBookId))) {
                    // Показываем информацию о корзине
                    cartAlertDialog(view.getContext(), "Успешно удалено из корзины. " + CART_TOTAL_MSG);
                }
            }
        });

        return view;
    }

    private void cartAlertDialog(Context context, String msg) {
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(context);
        dlgAlert.setMessage(String.format(msg, cart.getBookCount(), cart.getTotal()));
        dlgAlert.setTitle(R.string.app_name);
        dlgAlert.setPositiveButton(android.R.string.ok, null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }
}