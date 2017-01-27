package com.example.kolin.testgglads.presentation;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.kolin.testgglads.R;
import com.example.kolin.testgglads.domain.model.Post;
import com.example.kolin.testgglads.presentation.detail.DetailFragment;
import com.example.kolin.testgglads.presentation.list.ListFragment;
import com.example.kolin.testgglads.presentation.service.UpdateService;
import com.example.kolin.testgglads.presentation.webview.WebViewActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements
        ListFragment.OnInteractionListFragmentListener,
        DetailFragment.OnInteractDetailFragmentListener {


    private FragmentManager supportFragmentManager;
    private Fragment showingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        supportFragmentManager = getSupportFragmentManager();

        showingFragment = ListFragment.newInstance();

        if (savedInstanceState != null) {
            List<Fragment> fragments = supportFragmentManager.getFragments();
            for (int i = 0; i < fragments.size(); i++){
                if (fragments.get(i) != null){
                    showingFragment = fragments.get(i);
                }
            }

        }

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_activity_container, showingFragment)
                .commit();

        UpdateService.setServiceAlarmManager(this, true);
    }

    @Override
    public void onClick(Post post) {
        DetailFragment fragment = DetailFragment.newInstance(post);
        supportFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.main_activity_container, fragment)
                .commit();
    }



    @Override
    public void onClickGetIt(String url, String name) {
        Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("url", url);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        List<Fragment> fragments = supportFragmentManager.getFragments();
        for (Fragment f : fragments) {
            if (f != null)
                supportFragmentManager.putFragment(outState, f.getClass().getSimpleName(), f);
        }
    }


    public static Intent newIntent(Context context){
        return new Intent(context, MainActivity.class);
    }
}
