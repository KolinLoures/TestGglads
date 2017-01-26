package com.example.kolin.testgglads.presentation.webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.kolin.testgglads.R;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;
    private String currentUrl;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            currentUrl = bundle.getString("url");
            name = bundle.getString("name");
        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_web_view_toolbar);
        toolbar.setTitle(name);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        webView = (WebView) findViewById(R.id.web_view);


        if (savedInstanceState != null) {
            webView.restoreState(savedInstanceState);
        } else {
            setWebViewSettings();
        }



    }

    private void setWebViewSettings() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebClient());
        webView.loadUrl(currentUrl);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        webView.saveState(outState);
    }
}
