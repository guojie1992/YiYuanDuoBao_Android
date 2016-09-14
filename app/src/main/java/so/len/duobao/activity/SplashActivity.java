package so.len.duobao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
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
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    Intent intent = new Intent();

                    String isFirstOpen = Config.getInstance(SplashActivity.this).getConfig("isFirstOpen");
                    if (isFirstOpen == null || isFirstOpen.equals("")) {
                        intent.setClass(SplashActivity.this, LaunchActivity.class);
                    } else if (Config.getInstance(SplashActivity.this).getConfig("uid") == null || Config.getInstance(SplashActivity.this).getConfig("uid").isEmpty()) {
                        intent.setClass(SplashActivity.this, LoginActivity.class);
                    } else {
                        intent.setClass(SplashActivity.this, MainActivity.class);
                    }

                    startActivity(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.getBooleanExtra("isExit", false)) {
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

