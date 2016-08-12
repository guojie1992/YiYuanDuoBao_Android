package so.len.duobao.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import so.len.duobao.R;
import so.len.duobao.api.JS;
import so.len.duobao.customView.TopMenuBar;
import so.len.duobao.iPresenter.WebViewPresenter;
import so.len.duobao.iView.IWebView;
import so.len.duobao.utils.CommonUtils;

/**
 * Created by Chung on 2016/8/3.
 */
public class WebViewActivity extends WebBaseActivity implements IWebView {
    @BindView(R.id.tmb_activity_webview)
    TopMenuBar tmbActivityWebview;
    @BindView(R.id.fl_webview_activity_web)
    FrameLayout flWebviewActivityWeb;

    private WebViewPresenter webViewPresenter;
    private int statusHeight = 0;

    private WebView webView;
    private String url;
    private String title;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

            statusHeight = CommonUtils.getStatusHeight(this);
        }

        control();
    }

    private void control() {
        webViewPresenter = new WebViewPresenter(this);
        webViewPresenter.initView();
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        url = intent.getStringExtra(JS.H5_URL);
        title = intent.getStringExtra(JS.H5_TITLE);

        webView = getWebView(url);
        flWebviewActivityWeb.addView(webView);
        webView.addJavascriptInterface(new JS(this), "api");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        setWebZoom(webSettings);
        setNetworkCache(webSettings);
        webView.loadUrl(url);

        tmbActivityWebview.setMenuTopPadding(statusHeight);
        tmbActivityWebview.setBackSrc(View.VISIBLE);
        tmbActivityWebview.setBackSrc(R.mipmap.top_back);
        tmbActivityWebview.setTitleText(title);
        tmbActivityWebview.setTitleSecected(true);
        tmbActivityWebview.setMenuVisibility(View.INVISIBLE);
        tmbActivityWebview.setOnBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void reload() {
        webView.reload();
    }

}
