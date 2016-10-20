package so.len.duobao.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import so.len.duobao.R;
import so.len.duobao.api.HTML;
import so.len.duobao.api.JS;
import so.len.duobao.api.SERVER;
import so.len.duobao.bean.PersonalBean;
import so.len.duobao.customView.TopMenuBar;
import so.len.duobao.customView.iOSActionSheetDialog;
import so.len.duobao.http.Options;
import so.len.duobao.http.VolleyHttp;
import so.len.duobao.mPresenter.PersonalInfoPresenter;
import so.len.duobao.mView.IPersonalInfoView;
import so.len.duobao.utils.CommonUtils;

/**
 * Created by Chung on 2016/8/12.
 */
public class PersonalInfoActivity extends BaseActivity implements IPersonalInfoView {

    @BindView(R.id.tv_level_activity_personal_info)
    CircleImageView tvLevelActivityPersonalInfo;
    @BindView(R.id.tv_username_activity_personal_info)
    TextView tvUsernameActivityPersonalInfo;
    @BindView(R.id.tv_id_activity_personal_info)
    TextView tvIdActivityPersonalInfo;
    @BindView(R.id.top_menu_bar_personal_info)
    TopMenuBar topMenuBarPersonalInfo;

    private PersonalInfoPresenter personalInfoPresenter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        ButterKnife.bind(this);
        context = PersonalInfoActivity.this;
        topMenuBarPersonalInfo.setMenuTopPadding(statusHeight);
        control();
    }

    private void control() {
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

        personalInfoPresenter = new PersonalInfoPresenter(this, context);
        personalInfoPresenter.initView();
    }

    @Override
    public void initView(PersonalBean personalBean) {
        if (personalBean != null && personalBean.getData() != null) {
            Options opt = new Options();
            VolleyHttp.getInstance().imageLoader(SERVER.DOMAIN + personalBean.getData().getPath(), tvLevelActivityPersonalInfo, opt);
            tvUsernameActivityPersonalInfo.setText(personalBean.getData().getNickname());
            tvIdActivityPersonalInfo.setText(personalBean.getData().getUid());
        }
    }

    @OnClick({
            R.id.ll_head_pic_activity_personal_info,
            R.id.ll_username_activity_personal_info,
            R.id.ll_password_activity_personal_info,
            R.id.ll_share_activity_personal_info,
    })
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
            case R.id.ll_share_activity_personal_info:
                Intent intent3 = new Intent();
                intent3.setClass(PersonalInfoActivity.this, WebViewActivity.class);
                intent3.putExtra(JS.H5_TITLE, "推广二维码");
                intent3.putExtra(JS.H5_URL, HTML.CODE);
                intent3.putExtra("TOP_RIGHT", WebViewActivity.TOP_RIGHT.no_right_top);
                startActivity(intent3);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        personalInfoPresenter.initView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case iOSActionSheetDialog.TAKE:
                    if (CommonUtils.hasSDCard()) {
                        File tempFile = new File(Environment.getExternalStorageDirectory(), "head.png");
                        personalInfoPresenter.startPhotoZoom(Uri.fromFile(tempFile));
                    } else {
                        toast("未找到存储卡，无法存储照片！");
                    }
                    break;
                case iOSActionSheetDialog.CHOOSE:
                    personalInfoPresenter.startPhotoZoom(data.getData());
                    break;
                case iOSActionSheetDialog.CROP:
                    if (data != null) {
                        personalInfoPresenter.saveImage(data);
                    }
                    break;
            }

        }
    }
}
