package com.example.kolin.testgglads.presentation;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.example.kolin.testgglads.R;
import com.example.kolin.testgglads.domain.model.Post;
import com.example.kolin.testgglads.presentation.detail.DetailFragment;
import com.example.kolin.testgglads.presentation.list.ListFragment;
import com.example.kolin.testgglads.presentation.list.ListPresenter;
import com.example.kolin.testgglads.presentation.webview.WebViewActivity;

public class MainActivity extends AppCompatActivity implements
        ListFragment.OnInteractionListFragmentListener,
        DetailFragment.OnInteractDetailFragmentListener{


    private FragmentManager supportFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        supportFragmentManager = getSupportFragmentManager();
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_activity_container, ListFragment.newInstance())
                .commit();
    }

    @Override
    public void onClick(Post post) {
        supportFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.main_activity_container, DetailFragment.newInstance(post))
                .commit();

    }

    @Override
    public void onClickGetIt(String url, String name) {
        Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("url", url);
        startActivity(intent);
    }
}
