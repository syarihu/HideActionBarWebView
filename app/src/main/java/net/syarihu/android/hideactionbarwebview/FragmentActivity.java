package net.syarihu.android.hideactionbarwebview;

import android.graphics.PointF;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.webkit.WebView;

public class FragmentActivity extends AppCompatActivity implements TouchableWebView.OnWebViewTouchListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contents, WebViewFragment.newInstance("http://www.yahoo.co.jp"));
        transaction.commit();
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
