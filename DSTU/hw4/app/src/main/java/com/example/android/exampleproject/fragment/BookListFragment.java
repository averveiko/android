package com.example.android.exampleproject.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import com.example.android.exampleproject.domain.Book;
import com.example.android.exampleproject.repository.BookRepository;

public class BookListFragment extends ListFragment {
    private final BookRepository mBookRepository = BookRepository.getInstance();
    private OnFragmentItemClickListener mClickListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ArrayAdapter<Book> adapter=new ArrayAdapter<Book>(
                inflater.getContext(),
                android.R.layout.simple_list_item_1,
                mBookRepository.getBooks());
        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentItemClickListener) mClickListener = (OnFragmentItemClickListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mClickListener = null;
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if(mClickListener!=null) mClickListener.onFragmentItemClick((int)id);

    }
}
