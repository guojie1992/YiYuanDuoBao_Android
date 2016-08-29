package so.len.duobao.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;


import com.orhanobut.logger.Logger;

import org.apache.http.util.EncodingUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import so.len.duobao.R;
import so.len.duobao.api.HTML;
import so.len.duobao.api.JS;
import so.len.duobao.customView.TopMenuBar;
import so.len.duobao.database.Config;
import so.len.duobao.mPresenter.WebViewPresenter;
import so.len.duobao.mView.IWebView;
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
    private boolean isGoods;
    private String goodsID;
    //    private boolean needPost;
//    private String postData;
    private TOP_RIGHT top_right;
    private static final String errorUrl = "file:///android_asset/noInternet.html";

    public enum TOP_RIGHT {
        no_right_top, add_card, add_addr, my_recommend, save;
    }

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

        tmbActivityWebview.setMenuTopPadding(statusHeight);

        control();
    }

    private void control() {
        webViewPresenter = new WebViewPresenter(this);
        webViewPresenter.initView();
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        url = intent.getStringExtra(JS.H5_URL) + "&uid=" + Config.getInstance(WebViewActivity.this).getConfig("uid") ;
        title = intent.getStringExtra(JS.H5_TITLE);
        top_right = (TOP_RIGHT) intent.getSerializableExtra("TOP_RIGHT");

        isGoods = intent.getBooleanExtra("isGoods", false);
        if(isGoods){
            goodsID = intent.getStringExtra("goodsID");
            url = url + "&id=" + goodsID;
        }

        Logger.d(url);

//        needPost = intent.getBooleanExtra("needPost", false);
//        if(needPost){
//            postData = intent.getStringExtra("postData");
//        }

        tmbActivityWebview.setBackSrc(View.VISIBLE);
        tmbActivityWebview.setBackSrc(R.mipmap.top_back);
        tmbActivityWebview.setTitleText(title);
        tmbActivityWebview.setTitleSecected(true);
        tmbActivityWebview.setOnBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        switch (top_right) {
            case no_right_top:
                tmbActivityWebview.setMenuVisibility(View.INVISIBLE);
                break;
            case add_card:
                tmbActivityWebview.setMenuVisibility(View.VISIBLE);
                tmbActivityWebview.setMenuSrc(R.mipmap.add_card);
                tmbActivityWebview.setOnMenuClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = new Intent();
                        intent1.setClass(WebViewActivity.this, AddCardActivity.class);
//                        intent1.putExtra(JS.H5_TITLE, "添加银行卡");
//                        intent1.putExtra(JS.H5_URL, HTML.MY_ADD_CARD);
//                        intent1.putExtra("TOP_RIGHT", TOP_RIGHT.save);
                        startActivity(intent1);
                    }
                });
                break;
            case add_addr:
                tmbActivityWebview.setMenuVisibility(View.VISIBLE);
                tmbActivityWebview.setMenuSrc(R.mipmap.add_card);
                tmbActivityWebview.setOnMenuClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent2 = new Intent();
                        intent2.setClass(WebViewActivity.this, AddAddrActivity.class);
//                        intent2.putExtra(JS.H5_TITLE, "添加地址");
//                        intent2.putExtra(JS.H5_URL, HTML.MY_ADD_ADDR);
//                        intent2.putExtra("TOP_RIGHT", TOP_RIGHT.save);
                        startActivity(intent2);
                    }
                });
                break;
            case my_recommend:
                tmbActivityWebview.setMenuVisibility(View.VISIBLE);
                tmbActivityWebview.setMenuSrc(R.mipmap.my_recommend_top_right);
                tmbActivityWebview.setOnMenuClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent3 = new Intent();
                        intent3.setClass(WebViewActivity.this, WebViewActivity.class);
                        intent3.putExtra(JS.H5_TITLE, "我的佣金");
                        intent3.putExtra(JS.H5_URL, HTML.MY_RECOMMEND_GAIN);
                        intent3.putExtra("TOP_RIGHT", TOP_RIGHT.no_right_top);
                        startActivity(intent3);
                    }
                });
                break;
            case save:
                tmbActivityWebview.setMenuVisibility(View.VISIBLE);
                tmbActivityWebview.setMenuSrc(R.mipmap.save);
                tmbActivityWebview.setOnMenuClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toast("save");
                        finish();
                    }
                });
                break;
        }

        if(CommonUtils.isNetworkConnected(WebViewActivity.this)){
            webView = getWebView(url);
        } else {
            webView = getWebView(errorUrl);
        }

        flWebviewActivityWeb.addView(webView);
        webView.addJavascriptInterface(new JS(this), "api");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        setWebZoom(webSettings);
        setNetworkCache(webSettings);

        /***打开本地缓存提供JS调用**/
        webView.getSettings().setDomStorageEnabled(true);
// Set cache size to 8 mb by default. should be more than enough
        webView.getSettings().setAppCacheMaxSize(1024*1024*8);

// This next one is crazy. It's the DEFAULT location for your app's cache
// But it didn't work for me without this line.
// UPDATE: no hardcoded path. Thanks to Kevin Hawkins
        String appCachePath = getApplicationContext().getCacheDir().getAbsolutePath();
        webView.getSettings().setAppCachePath(appCachePath);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAppCacheEnabled(true);


//        if(needPost){
//            //由于webView.postUrl(url, postData)中 postData类型为byte[]
//            //通过EncodingUtils.getBytes(data, charset)方法进行转换
//            webView.postUrl(url, EncodingUtils.getBytes(postData, "BASE64"));
//        } else {
//            webView.loadUrl(url + "&" + postData);
//        }

        if(CommonUtils.isNetworkConnected(WebViewActivity.this)){
            webView.loadUrl(url);
        } else {
            webView.loadUrl(errorUrl);
        }

    }

    public void reload() {
        webView.reload();
    }

    public void reloadWebView(){
        if(CommonUtils.isNetworkConnected(WebViewActivity.this)){
            webView.post(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl(url);
                }
            });
        } else {
            toast("请检查网络后重试");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        reload();
    }
}
