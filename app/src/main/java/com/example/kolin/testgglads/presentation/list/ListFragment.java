package com.example.kolin.testgglads.presentation.list;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.kolin.testgglads.R;
import com.example.kolin.testgglads.domain.model.Post;

import java.util.List;


public class ListFragment extends Fragment implements ListView {

    private Toolbar toolbar;
    private Spinner spinner;
    private SwipeRefreshLayout swipeContainer;

    private ListPresenter listPresenter;
    private ListAdapter adapter;
    private RecyclerView recyclerView;

    private OnInteractionListFragmentListener listener;
    private ArrayAdapter<String> arrayAdapter;

    public ListFragment() {
        // Required empty public constructor
    }


    public static ListFragment newInstance() {
        ListFragment fragment = new ListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ListAdapter();
        arrayAdapter = new ArrayAdapter<String>(
                getContext(), R.layout.support_simple_spinner_dropdown_item);
        listPresenter = new ListPresenter();
        listPresenter.attacheView(this);
    }

    private void setAdapterListener() {
        if (adapter != null) {
            adapter.setListener(new ListAdapter.OnSelectedItemListener() {
                @Override
                public void onSelectedItem(Post selectedPost) {
                    if (listener != null)
                        listener.onClick(selectedPost);
                }
            });
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = getView() != null ? getView() :
                inflater.inflate(R.layout.fragment_list, container, false);

        toolbar = (Toolbar) view.findViewById(R.id.list_fragment_toolbar);
        setupToolBar();

        spinner = (Spinner) view.findViewById(R.id.list_fragment_spinner);
        spinner.setAdapter(arrayAdapter);
        setSpinnerListener();


        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.list_fragment_swipe_container);
        setupSwipeContainer();

        recyclerView = (RecyclerView) view.findViewById(R.id.list_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        setAdapterListener();

        return view;
    }



    private void setupSwipeContainer() {
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                listPresenter.loadCategoryPost(spinner.getSelectedItemPosition());
            }
        });

        swipeContainer.setColorSchemeResources(
                R.color.colorAccent);

    }

    private void setSpinnerListener() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
//                adapter.clear();
                listPresenter.loadCategoryPost(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (arrayAdapter.isEmpty()) {
            listPresenter.loadCategories();
        }
    }

    private void setupToolBar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar supportActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayShowTitleEnabled(false);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnInteractionListFragmentListener) {
            listener = (OnInteractionListFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString() +
                    "must implement OnInteractionListFragmentListener");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        swipeContainer.setOnRefreshListener(null);
        spinner.setOnItemSelectedListener(null);
        spinner.setAdapter(null);
        recyclerView.setAdapter(null);
        adapter.setListener(null);
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        listPresenter.disposeAll();
        listener = null;
        super.onDetach();
    }


    @Override
    public void showCategories(List<String> categoryList) {
        arrayAdapter.addAll(categoryList);
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void showPostsCategories(List<Post> postList) {
        adapter.clear();
        adapter.addAll(postList);
    }

    @Override
    public void setRefreshing(boolean b) {
        swipeContainer.setRefreshing(b);
    }

    @Override
    public void clearResult() {
        adapter.clear();
    }

    public interface OnInteractionListFragmentListener {
        void onClick(Post post);
    }
}
