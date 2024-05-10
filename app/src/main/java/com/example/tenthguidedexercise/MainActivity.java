package com.example.tenthguidedexercise;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    WebView browser;
    AutoCompleteTextView suggestedURL;
    ArrayAdapter<String> adapter;
    Button submit;
    String [] urls = {"google.com", "yahoo.com", "facebook.com", "youtube.com", "instagram.com", "amazon.com", "twitter.com",
            "linkedin.com", "reddit.com", "wikipedia.org", "ebay.com", "netflix.com", "pinterest.com", "wordpress.com",
            "apple.com", "bing.com", "paypal.com", "github.com", "stackoverflow.com", "microsoft.com","umak.edu.ph/"};

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ge10_web_view_auto_complete_text_view);

        browser = findViewById(R.id.webView);
        suggestedURL = findViewById(R.id.actvURLGE10);
        submit = findViewById(R.id.btnOpenURLGE10);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, urls);
        suggestedURL.setThreshold(2);
        suggestedURL.setAdapter(adapter);

        initializeWebView();
        loadWebURL();
    }

    public void initializeWebView(){
        browser.getSettings().setLoadsImagesAutomatically(true);
        // enabled java script
        browser.getSettings().setJavaScriptEnabled(true);
        // enabled Scrollbar
        browser.setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);

        // Set WebViewClient
        browser.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                // Show progress dialog when page starts loading
                progressDialog = ProgressDialog.show(MainActivity.this, "", "Loading...", true);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                // Dismiss progress dialog when page finishes loading
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        });
    }

    public void loadWebURL(){
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = suggestedURL.getText().toString();

                if(!url.startsWith("www.") && !url.startsWith("http://") ){
                    url = "www." + url;
                }
                if(!url.startsWith("http://") ){
                    url = "https://" + url;
                }
                browser.loadUrl(url);
            }
        });
    }
}
