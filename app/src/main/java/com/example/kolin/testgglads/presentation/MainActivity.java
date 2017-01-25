package com.example.kolin.testgglads.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.example.kolin.testgglads.R;
import com.example.kolin.testgglads.presentation.list.ListFragment;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_activity_container, ListFragment.newInstance("1", "1"))
                .commit();
    }

}
