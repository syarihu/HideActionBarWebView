package net.syarihu.android.hideactionbarwebview;

import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity implements TouchableWebView.OnWebViewTouchListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TouchableWebView touchableWebView = ((TouchableWebView)findViewById(R.id.webview));
        touchableWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        touchableWebView.loadUrl("http://www.yahoo.co.jp");
        touchableWebView.setOnWebViewTouchListener(this);
    }

    @Override
    public void onWebViewTouched(WebView webView, MotionEvent event, PointF oldPoint) {
        if(getSupportActionBar() == null) return;
        if(event.getAction() == MotionEvent.ACTION_MOVE) {
            // ActionBarが表示中で下にスクロールされた場合
            if(getSupportActionBar().isShowing() && oldPoint.y - event.getY() > 150) {
                // ActionBarを隠す
                getSupportActionBar().hide();
            }
            // ActionBarが非表示で上にスクロールされた場合
            else if(!getSupportActionBar().isShowing() && oldPoint.y - event.getY() < -150) {
                // ActionBarを表示する
                getSupportActionBar().show();
            }
        }
    }
}
