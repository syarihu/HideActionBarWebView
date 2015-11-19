package net.syarihu.android.hideactionbarwebview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by administrator on 15/11/20.
 */
public class WebViewFragment extends Fragment {
    private TouchableWebView.OnWebViewTouchListener mOnWebViewTouchListener;

    public static WebViewFragment newInstance(String url) {
        WebViewFragment fragment = new WebViewFragment();
        Bundle args = new Bundle();
        args.putString("url", url);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_webview, container, false);
        TouchableWebView touchableWebView = ((TouchableWebView)view.findViewById(R.id.webview));
        touchableWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        touchableWebView.loadUrl(getArguments().getString("url"));
        touchableWebView.setOnWebViewTouchListener(mOnWebViewTouchListener);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(!(context instanceof TouchableWebView.OnWebViewTouchListener)) return;
        mOnWebViewTouchListener = (TouchableWebView.OnWebViewTouchListener) context;
    }


}
