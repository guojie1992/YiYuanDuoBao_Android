package so.len.duobao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import so.len.duobao.R;
import so.len.duobao.customView.TopMenuBar;
import so.len.duobao.customView.iOSActionSheetDialog;
import so.len.duobao.iPresenter.PersonalInfoPresenter;
import so.len.duobao.iView.IPersonalInfoView;

/**
 * Created by Chung on 2016/8/12.
 */
public class PersonalInfoActivity extends BaseActivity implements IPersonalInfoView {

    @BindView(R.id.tv_level_activity_personal_info)
    CircleImageView tvLevelActivityPersonalInfo;
    @BindView(R.id.ll_head_pic_activity_personal_info)
    LinearLayout llHeadPicActivityPersonalInfo;
    @BindView(R.id.tv_username_activity_personal_info)
    TextView tvUsernameActivityPersonalInfo;
    @BindView(R.id.ll_username_activity_personal_info)
    LinearLayout llUsernameActivityPersonalInfo;
    @BindView(R.id.tv_id_activity_personal_info)
    TextView tvIdActivityPersonalInfo;
    @BindView(R.id.ll_id_activity_personal_info)
    LinearLayout llIdActivityPersonalInfo;
    @BindView(R.id.top_menu_bar_personal_info)
    TopMenuBar topMenuBarPersonalInfo;

    private PersonalInfoPresenter personalInfoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        ButterKnife.bind(this);
        control();
    }

    private void control() {
        personalInfoPresenter = new PersonalInfoPresenter(this);
        personalInfoPresenter.initView();
    }

    @Override
    public void initView() {
        topMenuBarPersonalInfo.setMenuTopPadding(statusHeight);
        topMenuBarPersonalInfo.setBackVisibility(View.VISIBLE);
        topMenuBarPersonalInfo.setBackSrc(R.mipmap.top_back);
        topMenuBarPersonalInfo.setTitleText("个人资料");
        topMenuBarPersonalInfo.setMenuVisibility(View.INVISIBLE);
        topMenuBarPersonalInfo.setOnBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick({R.id.ll_head_pic_activity_personal_info, R.id.ll_username_activity_personal_info, R.id.ll_password_activity_personal_info})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_head_pic_activity_personal_info:
                iOSActionSheetDialog dialog = new iOSActionSheetDialog(PersonalInfoActivity.this);
                dialog.builder()
                        .setCancelable(true)
                        .setCanceledOnTouchOutside(true)
                        .show();
                break;
            case R.id.ll_username_activity_personal_info:
                Intent intent1 = new Intent();
                intent1.setClass(PersonalInfoActivity.this, ChangeUsernameActivity.class);
                startActivity(intent1);
                break;
            case R.id.ll_password_activity_personal_info:
                Intent intent2 = new Intent();
                intent2.setClass(PersonalInfoActivity.this, ChangePasswordActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
