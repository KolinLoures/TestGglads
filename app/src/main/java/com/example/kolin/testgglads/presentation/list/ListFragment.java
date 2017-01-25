package com.example.kolin.testgglads.presentation.list;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.ExpandedMenuView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ScrollingTabContainerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.kolin.testgglads.R;
import com.example.kolin.testgglads.domain.model.Post;

import java.util.List;


public class ListFragment extends Fragment implements ListView {

    private Toolbar toolbar;
    private Spinner spinner;

    private ListPresenter listPresenter;
    private ListAdapter adapter;

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
        adapter = new ListAdapter();
        listPresenter = new ListPresenter();
        listPresenter.attacheView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        toolbar = (Toolbar) view.findViewById(R.id.list_fragment_toolbar);
        setupToolBar();

        spinner = (Spinner) view.findViewById(R.id.list_fragment_spinner);
//        setSpinnerListener();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void setSpinnerListener() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listPresenter.loadCategoryPost(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        listPresenter.loadCategories();
        setSpinnerListener();
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
        listPresenter.disposeAll();
        spinner.setOnItemSelectedListener(null);
        super.onDetach();
    }


    @Override
    public void showCategories(List<String> categoryList) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                getContext(), R.layout.support_simple_spinner_dropdown_item, categoryList);
        spinner.setAdapter(arrayAdapter);
    }

    @Override
    public void showPostsCategories(List<Post> postList) {
        adapter.clear();
        adapter.addAdll(postList);
    }
}
