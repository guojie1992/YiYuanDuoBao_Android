package so.len.duobao.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import so.len.duobao.R;
import so.len.duobao.customView.TopMenuBar;
import so.len.duobao.mPresenter.SettingsPresenter;
import so.len.duobao.mView.ISettingsView;

/**
 * Created by Chung on 2016/8/17.
 */
public class SettingsActivity extends BaseActivity implements ISettingsView {
    @BindView(R.id.top_menu_bar_settings)
    TopMenuBar topMenuBarSettings;
    @BindView(R.id.tv_version_activity_settings)
    TextView tvVersionActivitySettings;
    @BindView(R.id.ll_update_activity_settings)
    LinearLayout llUpdateActivitySettings;
    @BindView(R.id.ll_logout_activity_settings)
    LinearLayout llLogoutActivitySettings;

    private SettingsPresenter settingsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        topMenuBarSettings.setMenuTopPadding(statusHeight);
        control();
    }

    private void control() {
        settingsPresenter = new SettingsPresenter(this);
        settingsPresenter.initView();
    }


    @Override
    public void initView() {

        topMenuBarSettings.setBackVisibility(View.VISIBLE);
        topMenuBarSettings.setBackSrc(R.mipmap.top_back);
        topMenuBarSettings.setTitleText("设置");
        topMenuBarSettings.setMenuVisibility(View.INVISIBLE);
        topMenuBarSettings.setOnBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick({R.id.ll_update_activity_settings, R.id.ll_logout_activity_settings})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_update_activity_settings:
                toast("ll_update_activity_settings");
                break;
            case R.id.ll_logout_activity_settings:
                toast("ll_logout_activity_settings");
                break;
        }
    }
}
