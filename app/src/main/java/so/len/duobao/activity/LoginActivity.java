package so.len.duobao.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import so.len.duobao.R;
import so.len.duobao.customView.TopMenuBar;
import so.len.duobao.database.Config;
import so.len.duobao.mPresenter.LoginPresenter;
import so.len.duobao.mView.ILoginView;
import so.len.duobao.utils.CommonUtils;

/**
 * Created by Chung on 2016/8/5.
 */
public class LoginActivity extends BaseActivity implements ILoginView {
    @BindView(R.id.tmb_activity_login)
    TopMenuBar tmbActivityLogin;
    @BindView(R.id.et_activity_login_phone)
    EditText etActivityLoginPhone;
    @BindView(R.id.et_activity_login_password)
    EditText etActivityLoginPassword;
    @BindView(R.id.tv_forget_activity_login)
    TextView tvForgetActivityLogin;
    @BindView(R.id.btn_activity_login_submit)
    Button btnActivityLoginSubmit;

    private LoginPresenter loginPresenter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        context = LoginActivity.this;
        tmbActivityLogin.setMenuTopPadding(statusHeight);
        control();
    }

    private void control() {
        loginPresenter = new LoginPresenter(this, context);
        loginPresenter.initView();
    }


    @Override
    public void initView() {

        tmbActivityLogin.setTitleText("登陆");
        tmbActivityLogin.setBackVisibility(View.INVISIBLE);
        tmbActivityLogin.setMenuVisibility(View.VISIBLE);
        tmbActivityLogin.setMenuSrc(R.mipmap.top_register);
        tmbActivityLogin.setOnMenuClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public String getPhone() {
        return etActivityLoginPhone.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return etActivityLoginPassword.getText().toString().trim();
    }

    @Override
    public void clearEditText() {
        etActivityLoginPhone.setText("");
        etActivityLoginPassword.setText("");
    }

    @OnClick({R.id.tv_forget_activity_login, R.id.btn_activity_login_submit})
    public void onClick(View view) {
        if (CommonUtils.isFastClick()) {
            return ;
        }
        switch (view.getId()) {
            case R.id.tv_forget_activity_login:
                Intent intent1 = new Intent();
                intent1.setClass(LoginActivity.this, ForgetActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_activity_login_submit:
                loginPresenter.doLogin();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) { //监控/拦截/屏蔽返回键
            if (Config.getInstance(context).getConfig("uid") == null || Config.getInstance(context).getConfig("uid").isEmpty()){
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, SplashActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("isExit", true);
                startActivity(intent);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}
