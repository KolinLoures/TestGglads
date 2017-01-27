package com.example.kolin.testgglads.presentation.list;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kolin.testgglads.R;
import com.example.kolin.testgglads.domain.model.Category;
import com.example.kolin.testgglads.domain.model.Post;
import com.example.kolin.testgglads.presentation.service.UpdateService;

import java.io.Serializable;
import java.util.List;


public class ListFragment extends Fragment implements ListView {

    public static final String TAG = ListFragment.class.getSimpleName();

    private static final String ARG_LIST = "adapter_list";
    private static final String ARG_CAT = "array_list_categories";
    private static final String ARG_SELECT = "current_selection";

    private Toolbar toolbar;
    private Spinner spinner;
    private SwipeRefreshLayout swipeContainer;
    private FloatingActionButton fabSubscribe;

    private ListPresenter listPresenter;
    private ListAdapter adapter;
    private RecyclerView recyclerView;

    private OnInteractionListFragmentListener listener;
    private SpinnerCategoryAdapter arrayAdapter;

    public interface OnInteractionListFragmentListener {
        void onClick(Post post);
    }

    public ListFragment() {
        // Required empty public constructor
    }


    public static ListFragment newInstance() {
        ListFragment listFragment = new ListFragment();
        return listFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ListAdapter();

        arrayAdapter = new SpinnerCategoryAdapter(getContext(), R.layout.item_spinner);

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

        fabSubscribe = (FloatingActionButton) view.findViewById(R.id.list_fragment_fab_subscribe);
        setupFabSubscribe();

        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.list_fragment_swipe_container);
        setupSwipeContainer();

        recyclerView = (RecyclerView) view.findViewById(R.id.list_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        setAdapterListener();

        return view;
    }

    private void setupFabSubscribe() {
        fabSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category item = (Category) spinner.getSelectedItem();
                if (!listPresenter.isCategorySubscribed(getContext(), item.getSlug())) {
                    listPresenter.subscribeCategory(getContext(), item.getSlug());
                    listPresenter.setLastCountDownloadedPost(getContext(), adapter.getItemCount());
                    if (!UpdateService.isServiceAlarmOn(getActivity())){
                        UpdateService.setServiceAlarmManager(getActivity(), true);
                    }
                    Toast.makeText(getContext(), "Subscribe to notification", Toast.LENGTH_SHORT).show();
                } else {
                    UpdateService.setServiceAlarmManager(getActivity(), false);
                    listPresenter.subscribeCategory(getContext(), null);
                }
                setImageToFab();
            }
        });
    }

    private void setImageToFab() {
        Category selectedItem = (Category) spinner.getSelectedItem();
        if (!listPresenter.isCategorySubscribed(getContext(), selectedItem.getSlug())) {
            fabSubscribe.setImageResource(R.drawable.ic_subscribe_rss_button);
        } else {
            fabSubscribe.setImageResource(R.drawable.ic_done_white_24dp);
        }
    }

    private void setupSwipeContainer() {
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                int pos = spinner.getSelectedItemPosition();
                listPresenter.loadCategoryPost(arrayAdapter.getItem(pos), true);
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
                Category item = arrayAdapter.getItem(position);
                listPresenter.loadCategoryPost(item, false);

                if (!listPresenter.isCategorySubscribed(getContext(), item.getSlug())) {
                    fabSubscribe.setImageResource(R.drawable.ic_subscribe_rss_button);
                } else {
                    fabSubscribe.setImageResource(R.drawable.ic_done_white_24dp);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            int selectedPos = savedInstanceState.getInt(ARG_SELECT);
            spinner.setSelection(selectedPos);

            List<Category> categoryList = (List<Category>) savedInstanceState.getSerializable(ARG_CAT);
            arrayAdapter.addAll(categoryList);
            listPresenter.setCurrentCategoryId(arrayAdapter.getItem(selectedPos).getId());


            List<Post> postList = (List<Post>) savedInstanceState.getSerializable(ARG_LIST);
            if (postList != null && !postList.isEmpty()) {
                adapter.addAll(postList);
            } else {
                listPresenter.loadCategoryPost(arrayAdapter.getItem(selectedPos), true);
            }
        }
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
    public void onDetach() {
        swipeContainer.setOnRefreshListener(null);
        spinner.setOnItemSelectedListener(null);
        spinner.setAdapter(null);
        recyclerView.setAdapter(null);
        adapter.setListener(null);
        listPresenter.disposeAll();
        fabSubscribe.setOnClickListener(null);
        listener = null;
        super.onDetach();
    }


    @Override
    public void showCategories(List<Category> categoryList) {
        arrayAdapter.addAll(categoryList);
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


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable(ARG_LIST, (Serializable) adapter.getPostList());
        outState.putSerializable(ARG_CAT, (Serializable) arrayAdapter.getCategoryList());
        outState.putInt(ARG_SELECT, spinner.getSelectedItemPosition());
    }
}
