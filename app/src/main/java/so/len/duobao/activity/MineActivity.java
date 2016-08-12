package so.len.duobao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import so.len.duobao.R;
import so.len.duobao.customView.TopMenuBar;
import so.len.duobao.iPresenter.MinePresenter;
import so.len.duobao.iView.IMineView;

/**
 * Created by Chung on 2016/8/11.
 */
public class MineActivity extends BaseActivity implements IMineView {
    @BindView(R.id.top_menu_bar_mine)
    TopMenuBar topMenuBarMine;
    @BindView(R.id.iv_top_activity_mine)
    ImageView ivTopActivityMine;
    @BindView(R.id.tv_username_activity_mine)
    TextView tvUsernameActivityMine;
    @BindView(R.id.tv_signday_activity_mine)
    TextView tvSigndayActivityMine;
    @BindView(R.id.tv_m_activity_mine)
    TextView tvMActivityMine;
    @BindView(R.id.tv_point_activity_mine)
    TextView tvPointActivityMine;
    @BindView(R.id.civ_head_activity_mine)
    CircleImageView civHeadActivityMine;
    @BindView(R.id.tv_level_activity_mine)
    TextView tvLevelActivityMine;
    @BindView(R.id.tv_bank_activity_mine)
    TextView tvBankActivityMine;
    @BindView(R.id.tv_myrecommender_activity_mine)
    TextView tvMyrecommenderActivityMine;
    @BindView(R.id.tv_myback_activity_mine)
    TextView tvMybackActivityMine;
    @BindView(R.id.tv_mytreasure_activity_mine)
    TextView tvMytreasureActivityMine;
    @BindView(R.id.tv_myaddr_activity_mine)
    TextView tvMyaddrActivityMine;
    @BindView(R.id.iv_sign_activity_mine)
    ImageView ivSignActivityMine;

    private MinePresenter minePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        ButterKnife.bind(this);
        control();
    }

    private void control() {
        minePresenter = new MinePresenter(this);
        minePresenter.initView();
    }

    @Override
    public void initView() {
        topMenuBarMine.setTitleText("我的");
        topMenuBarMine.setBackVisibility(View.VISIBLE);
        topMenuBarMine.setBackSrc(R.mipmap.top_back);
        topMenuBarMine.setMenuVisibility(View.INVISIBLE);
        topMenuBarMine.setOnBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ivSignActivityMine.setImageResource(R.mipmap.my_sign);
    }

    @OnClick({R.id.civ_head_activity_mine,
            R.id.ll_sign_activity_mine,
            R.id.ll_myorder_activity_mine,
            R.id.ll_myrecommend_activity_mine,
            R.id.ll_mym_activity_mine,
            R.id.ll_mypoint_activity_mine,
            R.id.ll_level_activity_mine,
            R.id.ll_bank_activity_mine,
            R.id.ll_myrecommender_activity_mine,
            R.id.ll_myback_activity_mine,
            R.id.ll_mytreasure_activity_mine,
            R.id.ll_myaddr_activity_mine})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.civ_head_activity_mine:
                Intent intent = new Intent();
                intent.setClass(MineActivity.this, PersonalInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_sign_activity_mine:
                toast("signed");
                ivSignActivityMine.setImageResource(R.mipmap.my_signed);
                break;
            case R.id.ll_myorder_activity_mine:
                toast("ll_myorder_activity_mine");
                break;
            case R.id.ll_myrecommend_activity_mine:
                toast("ll_myrecommend_activity_mine");
                break;
            case R.id.ll_mym_activity_mine:
                toast("ll_mym_activity_mine");
                break;
            case R.id.ll_mypoint_activity_mine:
                toast("ll_mypoint_activity_mine");
                break;
            case R.id.ll_level_activity_mine:
                toast("ll_level_activity_mine");
                break;
            case R.id.ll_bank_activity_mine:
                toast("ll_bank_activity_mine");
                break;
            case R.id.ll_myrecommender_activity_mine:
                toast("ll_myrecommender_activity_mine");
                break;
            case R.id.ll_myback_activity_mine:
                toast("ll_myback_activity_mine");
                break;
            case R.id.ll_mytreasure_activity_mine:
                toast("ll_mytreasure_activity_mine");
                break;
            case R.id.ll_myaddr_activity_mine:
                toast("ll_myaddr_activity_mine");
                break;
        }
    }
}
