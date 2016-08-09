package so.len.duobao;

import android.app.Application;

import com.orhanobut.logger.Logger;

import so.len.duobao.bean.BaseBean;
import so.len.duobao.http.VolleyHttp;

/**
 * Created by Chung on 2016/8/3.
 */
public class App extends Application {
    public static BaseBean BASEBEAN;
    @Override
    public void onCreate() {
        super.onCreate();

        BASEBEAN = new BaseBean();
        VolleyHttp.getInstance().init(getApplicationContext());
        Logger.init("LOGGER");
    }
}
