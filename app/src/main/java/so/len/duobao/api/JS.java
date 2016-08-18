package so.len.duobao.api;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import so.len.duobao.R;
import so.len.duobao.activity.MainActivity;
import so.len.duobao.activity.WebViewActivity;
import so.len.duobao.database.Config;

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
     * @param title 标题栏标题
     * @param url   打开的网址
     */
    @JavascriptInterface
    public void openWEB(String title, String url) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(H5_URL, url);
        intent.putExtra(H5_TITLE, title);
        intent.putExtra("TOP_RIGHT", WebViewActivity.TOP_RIGHT.no_right_top);
        context.startActivity(intent);
    }

    /**
     * 显示吐司
     *
     * @param msg 要显示的消息
     */
    @JavascriptInterface
    public void toast(String msg) {
        Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 打印日志
     *
     * @param log 日志
     */
    @JavascriptInterface
    public void log(String log) {
        Log.i("h5", log);
    }

    /**
     * 存储数据
     *
     * @param key   键
     * @param value 值
     */
    @JavascriptInterface
    public void setConfig(String key, String value) {
        Config.getInstance(context).setConfig(key, value);
    }

    /**
     * 获取数据
     *
     * @param key 键
     */
    @JavascriptInterface
    public String getConfig(String key) {
        return Config.getInstance(context).getConfig(key);
    }

    /**
     * 返回
     */
    @JavascriptInterface
    public void back() {
        activity.finish();
        ((WebViewActivity) activity).reload();
    }

    /**
     * 拨打电话
     * @param phoneNumber 电话号码
     */
    @JavascriptInterface
    public void dial(String phoneNumber) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                callPhone(phoneNumber);
            } else {
                toast("没有权限：请为" + context.getResources().getString(R.string.app_name) + "开启拨打电话权限");
            }
        } else {
            callPhone(phoneNumber);
        }
    }

    void callPhone(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //noinspection ResourceType
        context.startActivity(intent);
    }

    /**
     * 返回商城
     */
    @JavascriptInterface
    public void backToMall(){
        Intent intent = new Intent();
        intent.setClass(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        activity.startActivity(intent);
    }

    /**
     * 返回首页
     */
    @JavascriptInterface
    public void backToIndex(){
        Intent intent = new Intent();
        intent.setClass(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
    }

}
