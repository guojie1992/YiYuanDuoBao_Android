package so.len.duobao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import so.len.duobao.R;

/**
 * Created by Chung on 2016/8/3.
 */
public class SplashActivity extends BaseActivity {
    @BindView(R.id.iv_splash)
    ImageView ivSplash;

    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
//        Config.getInstance(this).putConfig("statusHeight", String.valueOf(CommonUtils.getStatusHeight(this)));
        init();
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
    }

    private void init() {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}

