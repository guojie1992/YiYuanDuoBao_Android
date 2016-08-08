package so.len.duobao.api;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;

import so.len.duobao.activity.WebViewActivity;

/**
 * Created by Chung on 2016/8/3.
 */
public class JS {
    public static final String H5_URL = "h5_url";
    public static final String H5_TITLE = "h5_title";
    private final Context context;
    private final Activity activity;

    public JS(Context context) {
        this.context = context;
        this.activity = (Activity) context;
    }

    /**
     * 打开网页
     *
     * @param title 标题栏标题
     * @param url   打开的网址
     */
    @JavascriptInterface
    public void openWEB(String title, String url) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(H5_URL, url);
        intent.putExtra(H5_TITLE, title);
        context.startActivity(intent);
    }
}
