package so.len.duobao;

import android.app.Application;

import com.orhanobut.logger.Logger;

import cn.jpush.android.api.JPushInterface;
import so.len.duobao.http.VolleyHttp;

/**
 * Created by Chung on 2016/8/3.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        VolleyHttp.getInstance().init(getApplicationContext());
        Logger.init("LOGGER");

        JPushInterface.setDebugMode(true);  // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);  // 初始化 JPush
    }
}
