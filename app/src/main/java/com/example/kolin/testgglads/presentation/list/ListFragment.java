package com.example.kolin.testgglads.presentation.list;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.kolin.testgglads.R;

import java.util.List;


public class ListFragment extends Fragment implements ListView {

    private Toolbar toolbar;
    private Spinner spinner;

    private ListPresenter listPresenter;

    public ListFragment() {
        // Required empty public constructor
    }


    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listPresenter = new ListPresenter();
        listPresenter.attacheView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        toolbar = (Toolbar) view.findViewById(R.id.list_fragment_toolbar);
        spinner = (Spinner) view.findViewById(R.id.list_fragment_spinner);
        setupToolBar();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        listPresenter.loadCategories();
    }

    private void setupToolBar() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ActionBar supportActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayShowTitleEnabled(false);
        }
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void showCategories(List<String> categoryList) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                getContext(), R.layout.support_simple_spinner_dropdown_item, categoryList);
        spinner.setAdapter(arrayAdapter);
    }
}
