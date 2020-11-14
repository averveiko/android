package com.example.android.exampleproject.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.android.exampleproject.R;
import com.example.android.exampleproject.basket.Cart;

public class CartFragment extends Fragment {

    private final Cart cart = Cart.getInstance();

    public CartFragment() {
        // Required empty public constructor
    }

    public static CartFragment newInstance() {
        return new CartFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_cart, container, false);

        TextView txtCart = view.findViewById(R.id.txt_cart);

        txtCart.setText(String.format("Вы выбрали %s книг на общую сумму %s рублей.",
                cart.getBookCount(),
                cart.getTotal().toString()));

        return view;
    }
}