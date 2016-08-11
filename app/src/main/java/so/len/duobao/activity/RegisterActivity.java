package so.len.duobao.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import so.len.duobao.R;
import so.len.duobao.customView.TopMenuBar;
import so.len.duobao.iPresenter.RegisterPresenter;
import so.len.duobao.iView.IRegisterView;

/**
 * Created by Chung on 2016/8/5.
 */
public class RegisterActivity extends BaseActivity implements IRegisterView {
    @BindView(R.id.tmb_activity_register)
    TopMenuBar tmbActivityRegister;
    @BindView(R.id.et_activity_register_phone)
    EditText etActivityRegisterPhone;
    @BindView(R.id.btn_activity_register_getcode)
    Button btnActivityRegisterGetcode;
    @BindView(R.id.et_activity_register_code)
    EditText etActivityRegisterCode;
    @BindView(R.id.et_activity_register_password)
    EditText etActivityRegisterPassword;
    @BindView(R.id.btn_activity_register_submit)
    Button btnActivityRegisterSubmit;

    private RegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        control();
    }

    private void control() {
        registerPresenter = new RegisterPresenter(this);
        registerPresenter.initView();
    }

    @Override
    public void initView() {
        tmbActivityRegister.setTitleText("注册");
    }

    @Override
    public String getPhone() {
        return etActivityRegisterPhone.getText().toString().trim();
    }

    @Override
    public String getMessageCode() {
        return etActivityRegisterCode.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return etActivityRegisterPassword.getText().toString().trim();
    }

    @Override
    public void clearEditText() {
        etActivityRegisterPhone.setText("");
        etActivityRegisterCode.setText("");
        etActivityRegisterPassword.setText("");
    }

    @OnClick({R.id.btn_activity_register_getcode, R.id.btn_activity_register_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_activity_register_getcode:
                toast(registerPresenter.getServerCode());
                break;
            case R.id.btn_activity_register_submit:
                toast(registerPresenter.doRegister());
                break;
        }
    }
}
