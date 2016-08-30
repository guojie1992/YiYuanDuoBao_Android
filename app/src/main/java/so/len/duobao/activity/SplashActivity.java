package so.len.duobao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;
import so.len.duobao.R;
import so.len.duobao.database.Config;

/**
 * Created by Chung on 2016/8/3.
 */
public class SplashActivity extends BaseActivity {
    @BindView(R.id.iv_splash)
    ImageView ivSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        init();
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
    }

    private void init() {
        Intent intent = new Intent();
        if (Config.getInstance(SplashActivity.this).getConfig("uid") == null || Config.getInstance(SplashActivity.this).getConfig("uid").isEmpty()) {
            intent.setClass(this, LoginActivity.class);
        } else {
            intent.setClass(this, MainActivity.class);
        }
        startActivity(intent);
//        finish();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.getBooleanExtra("isExit", false)){
            finish();
            return;
        }
        if ((Intent.FLAG_ACTIVITY_CLEAR_TOP & intent.getFlags()) != 0) {
            init();
        }
    }

    @Override
    protected void onResume() {
        JPushInterface.onResume(SplashActivity.this);
        super.onResume();
    }

    @Override
    protected void onPause() {
        JPushInterface.onPause(SplashActivity.this);
        super.onPause();
    }
}

