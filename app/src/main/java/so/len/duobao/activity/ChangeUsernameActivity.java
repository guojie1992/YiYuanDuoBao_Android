package so.len.duobao.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import so.len.duobao.R;
import so.len.duobao.customView.TopMenuBar;
import so.len.duobao.iPresenter.ChangeUsernamePresenter;
import so.len.duobao.iView.IChangeUsernameView;

/**
 * Created by Chung on 2016/8/14.
 */
public class ChangeUsernameActivity extends BaseActivity implements IChangeUsernameView {
    @BindView(R.id.tmb_activity_change_username)
    TopMenuBar tmbActivityChangeUsername;
    @BindView(R.id.et_activity_change_username)
    EditText etActivityChangeUsername;

    private ChangeUsernamePresenter changeUsernamePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_username);
        ButterKnife.bind(this);
        control();
    }

    private void control() {
        changeUsernamePresenter = new ChangeUsernamePresenter(this);
        changeUsernamePresenter.initView();
    }

    @Override
    public void initView() {
        tmbActivityChangeUsername.setMenuTopPadding(statusHeight);
        tmbActivityChangeUsername.setBackVisibility(View.VISIBLE);
        tmbActivityChangeUsername.setBackSrc(R.mipmap.top_back);
        tmbActivityChangeUsername.setTitleText("设置会员名");
        tmbActivityChangeUsername.setMenuVisibility(View.VISIBLE);
        tmbActivityChangeUsername.setMenuSrc(R.mipmap.save);
        tmbActivityChangeUsername.setOnBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tmbActivityChangeUsername.setOnMenuClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast("保存");
                finish();
            }
        });
    }
}
