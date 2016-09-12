package so.len.duobao.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import so.len.duobao.R;
import so.len.duobao.customView.TopMenuBar;
import so.len.duobao.mPresenter.ChangePasswordPresenter;
import so.len.duobao.mView.IChangePasswordView;

/**
 * Created by Chung on 2016/8/17.
 */
public class ChangePasswordActivity extends BaseActivity implements IChangePasswordView {
    @BindView(R.id.tmb_activity_change_password)
    TopMenuBar tmbActivityChangePassword;
    @BindView(R.id.et_old_activity_change_password)
    EditText etOldActivityChangePassword;
    @BindView(R.id.et_new_activity_change_password)
    EditText etNewActivityChangePassword;
    @BindView(R.id.et_repeat_activity_change_password)
    EditText etRepeatActivityChangePassword;

    private Context context;
    private ChangePasswordPresenter changePasswordPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);
        this.context = ChangePasswordActivity.this;
        tmbActivityChangePassword.setMenuTopPadding(statusHeight);
        control();
    }

    private void control() {
        changePasswordPresenter = new ChangePasswordPresenter(this, context);
        changePasswordPresenter.initView();
    }

    @Override
    public void initView() {

        tmbActivityChangePassword.setBackVisibility(View.VISIBLE);
        tmbActivityChangePassword.setTitleVisibility(View.VISIBLE);
        tmbActivityChangePassword.setMenuVisibility(View.VISIBLE);
        tmbActivityChangePassword.setBackSrc(R.mipmap.top_back);
        tmbActivityChangePassword.setTitleText("修改密码");
        tmbActivityChangePassword.setMenuSrc(R.mipmap.save);
        tmbActivityChangePassword.setOnBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tmbActivityChangePassword.setOnMenuClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePasswordPresenter.changePassword(
                        etOldActivityChangePassword.getText().toString().trim(),
                        etNewActivityChangePassword.getText().toString().trim(),
                        etRepeatActivityChangePassword.getText().toString().trim()
                        );
//                finish();
            }
        });

    }
}
