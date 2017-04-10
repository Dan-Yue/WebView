package com.example.danyue.h5;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Timer;
import java.util.TimerTask;

public class WebTwoActivity extends Activity {
    private static final String TAG = "*WebTwoActivity";
    private boolean isRead = true;
    private String id;
    private WebView webview;
    //    private RelativeLayout parent;
    //    private View view;
    static int x;
    static int y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("*acticity", "WebOneActivity: " + (MainActivity.activityNum++));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        id = getIntent().getStringExtra("link");
        webview = (WebView) findViewById(R.id.WebView);
//        view = findViewById(R.id.view);
//        parent = (RelativeLayout) findViewById(R.id.web_gruop);

        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
                webview.clearHistory();
                webview.clearCache(true);
            }
        });
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100 && isRead) {
                    if (finalI == MainActivity.readEnd + 1) {
                        finalI = 0;
                    }
                    setScrollRead();
                    isRead = false;
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        webview.loadUrl("http://www.toutiao.com/i" + id + "/");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        startActivity(new Intent(WebTwoActivity.this, MainActivity.class));
        finish();
        return super.onKeyDown(keyCode, event);
    }

    private void setScrollRead() {
//        webview.setScrollY(1904);
        task.run();
        timer.schedule(task, MainActivity.readTime, MainActivity.readTime);
    }

    //模拟点击
    private void setSimulateClick(View view, float x, float y) {
        long downTime = SystemClock.uptimeMillis();
        final MotionEvent downEvent = MotionEvent.obtain(downTime, downTime,
                MotionEvent.ACTION_DOWN, x, y, 0);
        downTime += 1000;
        final MotionEvent upEvent = MotionEvent.obtain(downTime, downTime,
                MotionEvent.ACTION_UP, x, y, 0);
        view.onTouchEvent(downEvent);
        view.onTouchEvent(upEvent);
        downEvent.recycle();
        upEvent.recycle();
    }

    //模拟阅读
    private void imulateRead() {
        ViewTreeObserver vto2 = webview.getViewTreeObserver();
        vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                webview.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                x = webview.getHeight();
                y = webview.getHeight();
            }
        });

        new Thread() {
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        setSimulateClick(webview, 560, 930);
                    }

                });
            }
        }.start();

    }


    //得到网页点击的坐标
    static short click;

    private void getClickCoord() {
        webview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (click == 1) {
                        Log.d(TAG, "onTouch: " + event.getX() + "," + event.getY());
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    click = 1;
                } else if (event.getAction() == MotionEvent.ACTION_MOVE) {

                }
                return false;
            }
        });
    }

    //计算网页滑动距离
    private int scroll;

    @TargetApi(Build.VERSION_CODES.M)
    private void slidingDistance() {
        webview.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                scroll = scroll + scrollY - oldScrollY;
                Log.d(TAG, "onScrollChange: " + scroll);
            }
        });
    }

    private static int finalI = 0;
    private static Timer timer = new Timer();
    private TimerTask task = new TimerTask() {

        @Override
        public void run() {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if (finalI < 19) {
                        webview.setScrollY(100 * finalI);
                    } else if (finalI == 19) {
                        webview.setScrollY(1904);
                        imulateRead();
                    } else if (finalI < MainActivity.readEnd) {
                        webview.setScrollY(100 * finalI);
                    } else if (finalI == MainActivity.readEnd) {
                        task.cancel();
                        finish();
                        Intent intent = new Intent(WebTwoActivity.this, WebThreeActivity.class);
                        intent.putExtra("link", id);
                        startActivity(intent);
                    }
                    finalI++;
                }

            });
        }
    };
}
