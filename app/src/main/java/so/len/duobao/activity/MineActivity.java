package so.len.duobao.activity;

import android.content.Context;
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
import so.len.duobao.api.HTML;
import so.len.duobao.api.JS;
import so.len.duobao.api.SERVER;
import so.len.duobao.bean.MineBean;
import so.len.duobao.customView.TopMenuBar;
import so.len.duobao.http.Options;
import so.len.duobao.http.VolleyHttp;
import so.len.duobao.mPresenter.MinePresenter;
import so.len.duobao.mView.IMineView;
import so.len.duobao.utils.CommonUtils;

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
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        ButterKnife.bind(this);
        context = MineActivity.this;
        topMenuBarMine.setMenuTopPadding(statusHeight);
        control();
    }

    private void control() {
        minePresenter = new MinePresenter(this, context);
        minePresenter.initView();
    }

    @Override
    public void initView(MineBean mineBean) {
        topMenuBarMine.setTitleText("我的");
        topMenuBarMine.setBackVisibility(View.VISIBLE);
        topMenuBarMine.setBackSrc(R.mipmap.top_back);
        topMenuBarMine.setMenuVisibility(View.VISIBLE);
        topMenuBarMine.setMenuSrc(R.mipmap.settings);
        topMenuBarMine.setOnBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        topMenuBarMine.setOnMenuClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MineActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
        ivSignActivityMine.setImageResource(R.mipmap.my_sign);

        initViewFromData(mineBean);
    }

    private void initViewFromData(MineBean mineBean) {
        Options opt = new Options();
        VolleyHttp.getInstance().imageLoader(SERVER.DOMAIN + mineBean.getData().getPic(), civHeadActivityMine, opt);
        String vip = "0";
        switch (mineBean.getData().getVip()){
            case 0:
                vip = "普通会员";
                break;
            case 1:
                vip = "一级会员";
                break;
            case 2:
                vip = "二级会员";
                break;
            case 3:
                vip = "三级会员";
                break;
            case 4:
                vip = "四级会员";
                break;
            case 5:
                vip = "五级会员";
                break;
            case 6:
                vip = "黄金会员";
                break;
            default:
                vip = "null";
                break;
        }
        tvUsernameActivityMine.setText(mineBean.getData().getNickname() + "(" + vip + ")");
        tvSigndayActivityMine.setText(mineBean.getData().getSignIn() + "天");
        tvMActivityMine.setText(mineBean.getData().getMoneyBack());
        tvPointActivityMine.setText(mineBean.getData().getIntegralBack());
        tvMyrecommenderActivityMine.setText("ID:" + mineBean.getData().getPid());
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
        if (CommonUtils.isFastClick()) {
            return ;
        }
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.civ_head_activity_mine:
                intent.setClass(MineActivity.this, PersonalInfoActivity.class);
                break;
            case R.id.ll_sign_activity_mine:
                intent.setClass(MineActivity.this, WebViewActivity.class);
                intent.putExtra(JS.H5_TITLE, "签到");
                intent.putExtra(JS.H5_URL, HTML.SIGN);
                ivSignActivityMine.setImageResource(R.mipmap.my_signed);
                intent.putExtra("TOP_RIGHT", WebViewActivity.TOP_RIGHT.no_right_top);
                break;
            case R.id.ll_myorder_activity_mine:
                intent.setClass(MineActivity.this, WebViewActivity.class);
                intent.putExtra(JS.H5_TITLE, "我的订单");
                intent.putExtra(JS.H5_URL, HTML.MY_ORDER);
                intent.putExtra("TOP_RIGHT", WebViewActivity.TOP_RIGHT.no_right_top);
                break;
            case R.id.ll_myrecommend_activity_mine:
                intent.setClass(MineActivity.this, WebViewActivity.class);
                intent.putExtra(JS.H5_TITLE, "我推荐的人");
                intent.putExtra(JS.H5_URL, HTML.MY_RECOMMEND);
                intent.putExtra("TOP_RIGHT", WebViewActivity.TOP_RIGHT.my_recommend);
                break;
            case R.id.ll_mym_activity_mine:
                intent.setClass(MineActivity.this, WebViewActivity.class);
                intent.putExtra(JS.H5_TITLE, "我的M币");
                intent.putExtra(JS.H5_URL, HTML.MY_M);
                intent.putExtra("TOP_RIGHT", WebViewActivity.TOP_RIGHT.no_right_top);
                break;
            case R.id.ll_mypoint_activity_mine:
                intent.setClass(MineActivity.this, WebViewActivity.class);
                intent.putExtra(JS.H5_TITLE, "我的积分");
                intent.putExtra(JS.H5_URL, HTML.MY_POINT);
                intent.putExtra("TOP_RIGHT", WebViewActivity.TOP_RIGHT.no_right_top);
                break;
            case R.id.ll_level_activity_mine:
                intent.setClass(MineActivity.this, WebViewActivity.class);
                intent.putExtra(JS.H5_TITLE, "会员等级");
                intent.putExtra(JS.H5_URL, HTML.MY_LEVEL);
                intent.putExtra("TOP_RIGHT", WebViewActivity.TOP_RIGHT.no_right_top);
                break;
            case R.id.ll_bank_activity_mine:
                intent.setClass(MineActivity.this, WebViewActivity.class);
                intent.putExtra(JS.H5_TITLE, "我的银行卡");
                intent.putExtra(JS.H5_URL, HTML.MY_CARD);
                intent.putExtra("TOP_RIGHT", WebViewActivity.TOP_RIGHT.add_card);
                break;
            case R.id.ll_myrecommender_activity_mine:
                intent.setClass(MineActivity.this, ChangeRecommenderActivity.class);
                break;
            case R.id.ll_myback_activity_mine:
                intent.setClass(MineActivity.this, WebViewActivity.class);
                intent.putExtra(JS.H5_TITLE, "我的返还");
                intent.putExtra(JS.H5_URL, HTML.MY_BACK);
                intent.putExtra("TOP_RIGHT", WebViewActivity.TOP_RIGHT.no_right_top);
                break;
            case R.id.ll_mytreasure_activity_mine:
                intent.setClass(MineActivity.this, WebViewActivity.class);
                intent.putExtra(JS.H5_TITLE, "我的夺宝");
                intent.putExtra(JS.H5_URL, HTML.MY_TREASURE);
                intent.putExtra("TOP_RIGHT", WebViewActivity.TOP_RIGHT.no_right_top);
                break;
            case R.id.ll_myaddr_activity_mine:
                intent.setClass(MineActivity.this, WebViewActivity.class);
                intent.putExtra(JS.H5_TITLE, "送货地址");
                intent.putExtra(JS.H5_URL, HTML.MY_ADDR);
                intent.putExtra("TOP_RIGHT", WebViewActivity.TOP_RIGHT.add_addr);
                break;
        }
        startActivity(intent);
    }
}
