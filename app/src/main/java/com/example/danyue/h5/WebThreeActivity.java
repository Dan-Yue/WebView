package com.example.danyue.h5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class WebThreeActivity extends Activity {
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getIntent().getStringExtra("link");
        Intent intent = new Intent(WebThreeActivity.this,WebOneActivity.class);
        intent.putExtra("link",id);
        startActivity(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        id = getIntent().getStringExtra("link");
        Intent i = new Intent(WebThreeActivity.this,WebOneActivity.class);
        i.putExtra("link",id);
        startActivity(i);
        super.onNewIntent(intent);
    }

    //    private String id;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main3);
//        id = getIntent().getStringExtra("link");
//        WebView webview = (WebView) findViewById(R.id.WebView);
//        Button button = (Button) findViewById(R.id.finsh);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(WebThreeActivity.this, MainActivity.class));
//                finish();
//            }
//        });
//        webview.getSettings().setJavaScriptEnabled(true);
//        webview.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                return false;
//            }
//        });
//        webview.loadUrl("http://www.toutiao.com/i" + id + "/");
//
//    }
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        Intent intent = new Intent(this, WebOneActivity.class);
//        intent.putExtra("link", id);
//        startActivity(intent);
//        finish();
//        return super.onKeyDown(keyCode, event);
//    }
}
