package so.len.duobao.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.squareup.otto.Produce;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import so.len.duobao.R;
import so.len.duobao.activity.WebViewActivity;
import so.len.duobao.api.HTML;
import so.len.duobao.api.JS;
import so.len.duobao.bean.FiveBean;
import so.len.duobao.customAdapter.FragmentViewPagerAdapter;
import so.len.duobao.customView.FragmentViewPager;
import so.len.duobao.mPresenter.FivePresenter;
import so.len.duobao.mView.IFiveView;
import so.len.duobao.otto.AppBus;
import so.len.duobao.utils.CommonUtils;

/**
 * Created by Chung on 2016/8/8.
 */
public class FiveFragment extends BaseFragment implements IFiveView {
    @BindView(R.id.tv_my_fragment_five)
    TextView tvMyFragmentFive;
    @BindView(R.id.tv_history_fragment_five)
    TextView tvHistoryFragmentFive;
    @BindView(R.id.iv_indicator_fragment_five)
    View ivIndicatorFragmentFive;
    @BindView(R.id.mvp_goods_fragment_five)
    FragmentViewPager mvpGoodsFragmentFive;
    @BindView(R.id.tv_gifts_fragment_five)
    TextView tvGiftsFragmentFive;
    @BindView(R.id.tv_times_fragment_five)
    TextView tvTimesFragmentFive;
    @BindView(R.id.pv_progress_fragment_five)
    RoundCornerProgressBar pvProgressFragmentFive;
    @BindView(R.id.tv_mygoods_fragment_five)
    TextView tvMygoodsFragmentFive;
    @BindView(R.id.tv_mytickets_fragment_five)
    TextView tvMyticketsFragmentFive;
    @BindView(R.id.tv_mybeans_fragment_five)
    TextView tvMybeansFragmentFive;
    @BindView(R.id.sv_fragment_five)
    ScrollView svFragmentFive;
    @BindView(R.id.btn_go_fragment_five)
    Button btnGoFragmentFive;
    @BindView(R.id.tv_count_down_fragment_five)
    TextView tvCountDownFragmentFive;
    @BindView(R.id.ll_count_down_fragment_five)
    LinearLayout llCountDownFragmentFive;
    @BindView(R.id.tv_next_fragment_five)
    TextView tvNextFragmentFive;
    @BindView(R.id.tv_go_fragment_five)
    TextView tvGoFragmentFive;

    public static boolean isForeground = false;
    private Context context;
    public static FivePresenter fivePresenter;
    private FiveBean fiveBean;
    private int width;
    private MyGiftsFragment myGiftsFragment;
    private HistoryGiftsFragment historyGiftsFragment;
    private List<Fragment> fragmentList;
    private FragmentViewPagerAdapter adapter;
    private SimpleDateFormat sdf;

    private boolean isError = false;

    public Timer mTimer = new Timer();// 定时器
    private static final int MAXCOUNT = 100000000;//定数器
    private int count = 1;
    /**
     * 消息处理器的应用
     */
    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    fivePresenter.initView();
                    break;
                case 2:
                    mTimer.cancel();
                    mTimer = null;
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_five, null);
        ButterKnife.bind(this, view);
        context = getActivity();
        control();
        return view;
    }

    private void control() {
        fivePresenter = new FivePresenter(this, context);
        fivePresenter.initView();
    }

    @Override
    public void initView(FiveBean fiveBean) {
        this.fiveBean = fiveBean;

        if(fiveBean == null){
            return;
        } else {
            if(mTimer != null){
                mTimer.cancel();// 退出之前的mTimer
            }
            switch (fiveBean.getRob_list().getNext_time_status()) {
                case -1:
                    pvProgressFragmentFive.setVisibility(View.GONE);
                    llCountDownFragmentFive.setVisibility(View.VISIBLE);
                    btnGoFragmentFive.setBackgroundResource(R.mipmap.five_btn_finished);

                    tvGiftsFragmentFive.setText("");
                    tvGoFragmentFive.setText("没有新的抢钱任务");
                    tvTimesFragmentFive.setText("0");
                    tvNextFragmentFive.setText("");
                    tvCountDownFragmentFive.setText("");
                    break;
                case 0:
                    pvProgressFragmentFive.setVisibility(View.GONE);
                    llCountDownFragmentFive.setVisibility(View.VISIBLE);
                    btnGoFragmentFive.setBackgroundResource(R.mipmap.five_btn_finished);

                    tvGiftsFragmentFive.setText("");
                    tvGoFragmentFive.setText("该轮已结束");
                    tvTimesFragmentFive.setText(fiveBean.getRob_list().getRob_number());
                    tvNextFragmentFive.setText("离下一轮开始:");
                    tvCountDownFragmentFive.setText("");

                    sdf = new SimpleDateFormat("HH:mm:ss");
                    sdf.setTimeZone(TimeZone.getTimeZone("GMT+0"));
//                long time = 57600000;// 1970-01-01 08:00:00 加16小时，从1970-01-02 00:00:00开始计时

                    new CountDownTimer(1000 * fiveBean.getRob_list().getNext_time(), 1000) {//总时间， 间隔时间
                        public void onTick(long millisUntilFinished) {
                            tvCountDownFragmentFive.setText(sdf.format(millisUntilFinished));
                        }
                        public void onFinish() {
                            tvCountDownFragmentFive.setText("计时结束");
                            fivePresenter.initView();
                        }
                    }.start();
                    break;
                case 1:
                    pvProgressFragmentFive.setVisibility(View.VISIBLE);
                    llCountDownFragmentFive.setVisibility(View.GONE);
                    btnGoFragmentFive.setBackgroundResource(R.drawable.selector_go);//setImageResource(R.mipmap.five_btn_unfinish);

                    tvGiftsFragmentFive.setText(fiveBean.getRob_list().getRob_copies());
                    tvGoFragmentFive.setText("份商品/代金券");
                    tvTimesFragmentFive.setText(fiveBean.getRob_list().getRob_number());
                    tvNextFragmentFive.setText("");
                    tvCountDownFragmentFive.setText("");

                    pvProgressFragmentFive.setProgress(Float.parseFloat(fiveBean.getRob_list().getProgress_bar()));

                    mTimer = new Timer();// new一个Timer,否则会报错
                    timerTask(); // 定时执行

                    break;
                default:
                    break;
            }


            tvMygoodsFragmentFive.setText(fiveBean.getHtml_list().getGoods_count());
            tvMyticketsFragmentFive.setText(fiveBean.getHtml_list().getVouchers_count());
            tvMybeansFragmentFive.setText(fiveBean.getHtml_list().getBeans());

            mvpGoodsFragmentFive.setDisplayMode(FragmentViewPager.DisplayMode.DISPLAY_BY_EVERY_ONE);//每个页面高度自适应

            Point outSize = new Point();
            getActivity().getWindowManager().getDefaultDisplay().getSize(outSize);
            width = outSize.x / 2;
            ViewGroup.LayoutParams lp = ivIndicatorFragmentFive.getLayoutParams();
            lp.width = width;
            ivIndicatorFragmentFive.setLayoutParams(lp);

            if(myGiftsFragment==null && historyGiftsFragment==null){
                myGiftsFragment = new MyGiftsFragment();
                historyGiftsFragment = new HistoryGiftsFragment();
            }

            AppBus.getInstance().post(produceFiveBean());

            if (adapter == null) {
                fragmentList = new ArrayList<>();
                fragmentList.add(myGiftsFragment);
                fragmentList.add(historyGiftsFragment);
                adapter = new FragmentViewPagerAdapter(getActivity().getSupportFragmentManager(), fragmentList);
                mvpGoodsFragmentFive.setAdapter(adapter);
            } else {
                myGiftsFragment.onFiveBean(fiveBean);
                historyGiftsFragment.onFiveBean(fiveBean);
                adapter.notifyDataSetChanged();
            }
            tvMyFragmentFive.setSelected(true);
            tvMyFragmentFive.setTextColor(getActivity().getResources().getColor(R.color.theme));
            mvpGoodsFragmentFive.setScrollable(false);
            mvpGoodsFragmentFive.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) ivIndicatorFragmentFive.getLayoutParams();
                    lp.leftMargin = (int) (position * width + positionOffset * width);
                    ivIndicatorFragmentFive.setLayoutParams(lp);
                }
                @Override
                public void onPageSelected(int position) {
                    mvpGoodsFragmentFive.requestLayout();//重新适应布局
                    tvMyFragmentFive.setSelected(false);
                    tvHistoryFragmentFive.setSelected(false);
                    switch (position) {
                        case 0:
                            tvMyFragmentFive.setSelected(true);
                            tvMyFragmentFive.setTextColor(getActivity().getResources().getColor(R.color.theme));
                            tvHistoryFragmentFive.setTextColor(getActivity().getResources().getColor(R.color.black));
                            break;
                        case 1:
                            tvHistoryFragmentFive.setSelected(true);
                            tvHistoryFragmentFive.setTextColor(getActivity().getResources().getColor(R.color.theme));
                            tvMyFragmentFive.setTextColor(getActivity().getResources().getColor(R.color.black));
                            break;
                    }
                }
                @Override
                public void onPageScrollStateChanged(int state) {
//                logDebug("state:" + String.valueOf(state));
                }
            });
        }
        isError = false;
    }


    @Override
    public void initErrorView() {
        isError = true;
        fivePresenter.initView();
    }

    @OnClick({
            R.id.tv_my_fragment_five,
            R.id.tv_history_fragment_five,
            R.id.btn_go_fragment_five,
            R.id.ll_goods_fragment_five,
            R.id.ll_tickets_fragment_five,
            R.id.ll_beans_fragment_five
    })
    public void onClick(View view) {
        if (CommonUtils.isFastClick()) {
            return;
        }
        switch (view.getId()) {
            case R.id.tv_my_fragment_five:
                mvpGoodsFragmentFive.setCurrentItem(0);
                break;
            case R.id.tv_history_fragment_five:
                mvpGoodsFragmentFive.setCurrentItem(1);
                break;
            case R.id.btn_go_fragment_five:
                if(!isError){
                    if(fiveBean.getRob_list().getNext_time_status() == 1){
                        fivePresenter.go();
//                        fivePresenter.initView();
                    } else {
                        toast("不在抢钱时间");
                    }
                } else {
                    toast("没有门票或不在抢钱时间");
                }
                break;
            case R.id.ll_goods_fragment_five:
                Intent intent1 = new Intent();
                intent1.setClass(getActivity(), WebViewActivity.class);
                intent1.putExtra(JS.H5_TITLE, "我的商品");
                intent1.putExtra(JS.H5_URL, HTML.GIFT_GOODS);
                intent1.putExtra("TOP_RIGHT", WebViewActivity.TOP_RIGHT.no_right_top);
                startActivity(intent1);
                break;
            case R.id.ll_tickets_fragment_five:
                Intent intent2 = new Intent();
                intent2.setClass(getActivity(), WebViewActivity.class);
                intent2.putExtra(JS.H5_TITLE, "我的代金券");
                intent2.putExtra(JS.H5_URL, HTML.GIFT_TICKETS);
                intent2.putExtra("TOP_RIGHT", WebViewActivity.TOP_RIGHT.no_right_top);
                startActivity(intent2);
                break;
            case R.id.ll_beans_fragment_five:
                Intent intent3 = new Intent();
                intent3.setClass(getActivity(), WebViewActivity.class);
                intent3.putExtra(JS.H5_TITLE, "我的欢乐豆");
                intent3.putExtra(JS.H5_URL, HTML.GIFT_BEANS);
                intent3.putExtra("TOP_RIGHT", WebViewActivity.TOP_RIGHT.no_right_top);
                startActivity(intent3);
                break;
        }
    }

    @Override
    public void onResume() {
        isForeground = true;
        AppBus.getInstance().register(this);
        fivePresenter.initView();
        super.onResume();
    }

    @Override
    public void onPause() {
        mTimer.cancel();// 程序退出时cancel timer
        isForeground = false;
        AppBus.getInstance().unregister(this);
        super.onPause();
    }

    @Produce
    public FiveBean produceFiveBean() {
        return fiveBean;
    }


    public void timerTask() {
        //创建定时线程执行更新任务
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(count <= MAXCOUNT){
                    mHandler.sendEmptyMessage(1);// 向Handler发送消息
                }else{
                    mHandler.sendEmptyMessage(2);// 向Handler发送消息停止继续执行
                }
                count++;
            }
        }, 5000, 5000);// 定时任务
    }

}
