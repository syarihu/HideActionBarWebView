package net.syarihu.android.hideactionbarwebview;

import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;

/**
 * Created by administrator on 15/11/20.
 */
public class TouchableWebView extends WebView {
    private OnWebViewTouchListener mOnWebViewTouchListener;
    private PointF mOldPoint;

    public interface OnWebViewTouchListener {
        void onWebViewTouched(WebView webView, MotionEvent newEvent, PointF oldPoint);
    }

    public TouchableWebView(Context context) {
        super(context);
    }

    public TouchableWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            mOldPoint = new PointF(event.getX(), event.getY());
        }
        if(mOnWebViewTouchListener != null) {
            mOnWebViewTouchListener.onWebViewTouched(this, event, mOldPoint);
        }
        return super.onTouchEvent(event);
    }

    public void setOnWebViewTouchListener(OnWebViewTouchListener onWebViewTouchListener) {
        mOnWebViewTouchListener = onWebViewTouchListener;
    }
}
