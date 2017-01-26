package com.example.kolin.testgglads.presentation.detail;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kolin.testgglads.R;
import com.example.kolin.testgglads.domain.model.Post;
import com.squareup.picasso.Picasso;


public class DetailFragment extends Fragment {

    private static final String ARG_KEY = "post_key";

    private Post currentPost;
    private Toolbar toolbar;
    private TextView description;
    private TextView rating;

    public DetailFragment() {
        // Required empty public constructor
    }


    public static DetailFragment newInstance(Post selectedPost) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_KEY, selectedPost);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Bundle arguments = getArguments();
        if (arguments != null) {
            currentPost = arguments.getParcelable(ARG_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        ImageView screenShot = (ImageView) view.findViewById(R.id.detail_fragment_image);

        description = (TextView) view.findViewById(R.id.detail_text_description);
        rating = (TextView) view.findViewById(R.id.detail_text_rating);

        toolbar = (Toolbar) view.findViewById(R.id.detail_fragment_toolbar);
        setupToolbar();

        Picasso
                .with(getContext())
                .load(currentPost.getScreenshotsUrl().get(0))
                .placeholder(R.color.colorWhite)
                .into(screenShot);

        description.setText(currentPost.getTagLine());
        rating.setText(String.valueOf(currentPost.getVotesCount()));

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onDetach() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(null);
        super.onDetach();
    }

    private void setupToolbar(){
        toolbar.setTitle(currentPost.getName());
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        ActionBar supportActionBar = activity.getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

}
