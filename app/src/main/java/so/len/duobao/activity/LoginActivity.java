package so.len.duobao.activity;

import android.os.Bundle;
import android.widget.EditText;

import com.rey.material.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import so.len.duobao.R;
import so.len.duobao.customView.TopMenuBar;
import so.len.duobao.iPresenter.LoginPresenter;
import so.len.duobao.iView.ILoginView;

/**
 * Created by Chung on 2016/8/5.
 */
public class LoginActivity extends BaseActivity implements ILoginView{
    @BindView(R.id.tmb_activity_login)
    TopMenuBar tmbActivityLogin;
    @BindView(R.id.et_activity_login_phone)
    EditText etActivityLoginPhone;
    @BindView(R.id.et_activity_login_password)
    EditText etActivityLoginPassword;
    @BindView(R.id.btn_activity_login_submit)
    Button btnActivityLoginSubmit;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        control();
    }

    private void control() {
        loginPresenter = new LoginPresenter(this);
        loginPresenter.setTopMenu();
    }

    @OnClick(R.id.btn_activity_login_submit)
    public void onClick() {
        toast(loginPresenter.doLogin(loginPresenter.getPhone(), loginPresenter.getPassword()));
        loginPresenter.clearEditText();
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

    @Override
    public void setTopMenu() {
        tmbActivityLogin.setTitleText("登陆");
    }
}
