package so.len.duobao.fragment;

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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import so.len.duobao.R;
import so.len.duobao.customAdapter.FragmentViewPagerAdapter;
import so.len.duobao.customView.MyViewPager;
import so.len.duobao.iPresenter.FivePresenter;
import so.len.duobao.iView.IFiveView;

/**
 * Created by Chung on 2016/8/8.
 */
public class FiveFragment extends BaseFragment implements IFiveView {
    @BindView(R.id.iv_top_fragment_five)
    ImageView ivTopFragmentFive;
    @BindView(R.id.tv_my_fragment_five)
    TextView tvMyFragmentFive;
    @BindView(R.id.tv_history_fragment_five)
    TextView tvHistoryFragmentFive;
    @BindView(R.id.iv_indicator_fragment_five)
    ImageView ivIndicatorFragmentFive;
    @BindView(R.id.mvp_goods_fragment_five)
    MyViewPager mvpGoodsFragmentFive;

    private FivePresenter fivePresenter;
    private int width;
    private MyGiftsFragment myGiftsFragment;
    private HistoryGiftsFragment historyGiftsFragment;
    private FragmentViewPagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_five, null);
        ButterKnife.bind(this, view);
        control();
        return view;
    }

    private void control() {
        fivePresenter = new FivePresenter(this);
        fivePresenter.initView();
    }

    @Override
    public void initView() {
        initBtn();
        initGiftsViewPager();
        initMyGifts();
        initHistroyGifts();
    }

    private void initBtn() {

    }

    private void initGiftsViewPager() {
        Point outSize = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(outSize);
        width = outSize.x / 2;
        ViewGroup.LayoutParams lp = ivIndicatorFragmentFive.getLayoutParams();
        lp.width = width;
        ivIndicatorFragmentFive.setLayoutParams(lp);

        myGiftsFragment = new MyGiftsFragment();
        historyGiftsFragment = new HistoryGiftsFragment();

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
                logDebug("state:" + String.valueOf(state));
            }
        });
    }

    private void initMyGifts() {

    }

    private void initHistroyGifts() {

    }

}
