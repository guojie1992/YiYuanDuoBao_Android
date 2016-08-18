package so.len.duobao.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import so.len.duobao.R;
import so.len.duobao.customView.TopMenuBar;
import so.len.duobao.mPresenter.LoginPresenter;
import so.len.duobao.mView.ILoginView;

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
        control();
    }

    private void control() {
        loginPresenter = new LoginPresenter(this);
        loginPresenter.initView();
    }


    @Override
    public void initView() {
        tmbActivityLogin.setMenuTopPadding(statusHeight);
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
        switch (view.getId()) {
            case R.id.tv_forget_activity_login:
                Intent intent1 = new Intent();
                intent1.setClass(LoginActivity.this, ForgetActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_activity_login_submit:
                loginPresenter.doLogin(context);
                break;
        }
    }
}
