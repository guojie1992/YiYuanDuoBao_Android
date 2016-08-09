package so.len.duobao.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.orhanobut.logger.Logger;


/**
 * Created by Chung on 2016/8/3.
 */
public class BaseActivity extends FragmentActivity {
    protected int statusHeight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

//            statusHeight= CommonUtils.getStatusHeight(this);
        }
    }
    protected void toast(String text){
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
    }
    protected void logInfo(String text){
        Logger.i(text);
    }
    protected void logError(String text){
        Logger.e(text);
    }
    protected void logWarn(String text){
        Logger.w(text);
    }
    protected void logDebug(String text){
        Logger.d(text);
    }
    protected void logVerbose(String text){
        Logger.v(text);
    }
}
