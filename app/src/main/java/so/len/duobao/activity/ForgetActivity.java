package so.len.duobao.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.rey.material.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import so.len.duobao.R;
import so.len.duobao.customView.TopMenuBar;
import so.len.duobao.iPresenter.ForgetPresenter;
import so.len.duobao.iView.IForgetView;

/**
 * Created by Chung on 2016/8/5.
 */
public class ForgetActivity extends BaseActivity implements IForgetView {
    @BindView(R.id.tmb_activity_forget)
    TopMenuBar tmbActivityForget;
    @BindView(R.id.et_activity_forget_phone)
    EditText etActivityForgetPhone;
    @BindView(R.id.et_activity_forget_code)
    EditText etActivityForgetCode;
    @BindView(R.id.btn_activity_forget_getcode)
    Button btnActivityForgetGetcode;
    @BindView(R.id.et_activity_forget_password)
    EditText etActivityForgetPassword;
    @BindView(R.id.et_activity_forget_password_repeat)
    EditText etActivityForgetPasswordRepeat;
    @BindView(R.id.btn_activity_forget_submit)
    Button btnActivityForgetSubmit;

    private ForgetPresenter forgetPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        ButterKnife.bind(this);
        control();
    }

    private void control() {
        forgetPresenter = new ForgetPresenter(this);
        forgetPresenter.initView();
    }

    @OnClick({R.id.btn_activity_forget_getcode, R.id.btn_activity_forget_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_activity_forget_getcode:
                toast(forgetPresenter.getServerCode());
                break;
            case R.id.btn_activity_forget_submit:
                toast(forgetPresenter.doForget());
                break;
        }
    }


    @Override
    public void initView() {
        tmbActivityForget.setTitleText("忘记密码");
    }

    @Override
    public String getPhone() {
        return etActivityForgetPhone.getText().toString().trim();
    }

    @Override
    public String getMessageCode() {
        return etActivityForgetCode.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return etActivityForgetPassword.getText().toString().trim();
    }

    @Override
    public String getRepeatPassword() {
        return etActivityForgetPasswordRepeat.getText().toString().trim();
    }

    @Override
    public void clearEditText() {
        etActivityForgetPhone.setText("");
        etActivityForgetCode.setText("");
        etActivityForgetPassword.setText("");
        etActivityForgetPasswordRepeat.setText("");
    }

}
