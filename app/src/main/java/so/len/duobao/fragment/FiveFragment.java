package so.len.duobao.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.squareup.otto.Produce;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import so.len.duobao.R;
import so.len.duobao.activity.WebViewActivity;
import so.len.duobao.api.HTML;
import so.len.duobao.api.JS;
import so.len.duobao.bean.FiveBean;
import so.len.duobao.customAdapter.FragmentViewPagerAdapter;
import so.len.duobao.customView.MyViewPager;
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
    MyViewPager mvpGoodsFragmentFive;
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
    ImageView btnGoFragmentFive;

    private Context context;
    private FivePresenter fivePresenter;
    private FiveBean fiveBean;
    private int width;
    private MyGiftsFragment myGiftsFragment;
    private HistoryGiftsFragment historyGiftsFragment;
    private FragmentViewPagerAdapter adapter;

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

        tvGiftsFragmentFive.setText(fiveBean.getRob_list().getRob_copies() + "份商品/代金券");
        tvTimesFragmentFive.setText("今日还可抢" + fiveBean.getRob_list().getRob_number() + "次");
        pvProgressFragmentFive.setProgress(Float.parseFloat("0.8"));

        tvMygoodsFragmentFive.setText(fiveBean.getHtml_list().getGoods_count());
        tvMyticketsFragmentFive.setText(fiveBean.getHtml_list().getVouchers_count());
        tvMybeansFragmentFive.setText(fiveBean.getHtml_list().getBeans());

        mvpGoodsFragmentFive.setDisplayMode(MyViewPager.DisplayMode.FRAGMENT_FIVE);

        Point outSize = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(outSize);
        width = outSize.x / 2;
        ViewGroup.LayoutParams lp = ivIndicatorFragmentFive.getLayoutParams();
        lp.width = width;
        ivIndicatorFragmentFive.setLayoutParams(lp);

        myGiftsFragment = new MyGiftsFragment();
        historyGiftsFragment = new HistoryGiftsFragment();

        AppBus.getInstance().post(produceFiveBean());

        if (adapter == null) {
            List<Fragment> fragmentList = new ArrayList<>();
            fragmentList.add(myGiftsFragment);
            fragmentList.add(historyGiftsFragment);
            adapter = new FragmentViewPagerAdapter(getActivity().getSupportFragmentManager(), fragmentList);
        }
        mvpGoodsFragmentFive.setAdapter(adapter);
        tvMyFragmentFive.setSelected(true);
        tvMyFragmentFive.setTextColor(getActivity().getResources().getColor(R.color.theme));
        mvpGoodsFragmentFive.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) ivIndicatorFragmentFive.getLayoutParams();
                lp.leftMargin = (int) (position * width + positionOffset * width);
                ivIndicatorFragmentFive.setLayoutParams(lp);
            }
            @Override
            public void onPageSelected(int position) {
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
            return ;
        }
        switch (view.getId()) {
            case R.id.tv_my_fragment_five:
                mvpGoodsFragmentFive.setCurrentItem(0);
                break;
            case R.id.tv_history_fragment_five:
                mvpGoodsFragmentFive.setCurrentItem(1);
                break;
            case R.id.btn_go_fragment_five:
                toast("go");
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
        AppBus.getInstance().register(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        AppBus.getInstance().unregister(this);
        super.onPause();
    }

    @Produce
    public FiveBean produceFiveBean(){
        return fiveBean;
    }

}
