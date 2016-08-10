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
import butterknife.OnClick;
import so.len.duobao.R;
import so.len.duobao.customAdapter.FragmentViewPagerAdapter;
import so.len.duobao.customView.MyViewPager;
import so.len.duobao.http.Options;
import so.len.duobao.http.VolleyHttp;
import so.len.duobao.iPresenter.TwoPresenter;
import so.len.duobao.iView.ITwoView;

/**
 * Created by Chung on 2016/8/3.
 */
public class TwoFragment extends BaseFragment implements ITwoView {
    @BindView(R.id.iv_top_fragment_two)
    ImageView ivTopFragmentTwo;
    @BindView(R.id.tv_points_fragment_two)
    TextView tvPointsFragmentTwo;
    @BindView(R.id.tv_m_fragment_two)
    TextView tvMFragmentTwo;
    @BindView(R.id.iv_indicator_fragment_two)
    ImageView ivIndicatorFragmentTwo;
    @BindView(R.id.mvp_goods_fragment_two)
    MyViewPager mvpGoodsFragmentTwo;

    private TwoPresenter twoPresenter;
    private int width;
    private PointGoodsFragment pointGoodsFragment;
    private MGoodsFragment mGoodsFragment;
    private FragmentViewPagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, null);
        ButterKnife.bind(this, view);

        control();

        return view;
    }

    private void control() {
        twoPresenter = new TwoPresenter(this);
       twoPresenter.initView();
    }

    @Override
    public void initView() {
        initImg();
        initGoodsViewPager();
        initGoalsGoods();
        initMGoods();
    }

    private void initImg() {
        VolleyHttp vh = VolleyHttp.getInstance();
        Options opt = new Options();
//        opt.defImage(R.mipmap.ic_launcher)
//                .errImage(R.mipmap.ic_launcher);
        vh.imageLoader("http://pic73.nipic.com/file/20150722/19795594_122255146861_2.jpg", ivTopFragmentTwo, null);
    }

    private void initGoodsViewPager() {
        Point outSize = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(outSize);
        width = outSize.x / 2;
        ViewGroup.LayoutParams lp = ivIndicatorFragmentTwo.getLayoutParams();
        lp.width = width;
        ivIndicatorFragmentTwo.setLayoutParams(lp);

        pointGoodsFragment = new PointGoodsFragment();
        mGoodsFragment = new MGoodsFragment();

        if (adapter == null) {
            List<Fragment> fragmentList = new ArrayList<>();
            fragmentList.add(pointGoodsFragment);
            fragmentList.add(mGoodsFragment);
            adapter = new FragmentViewPagerAdapter(getActivity().getSupportFragmentManager(), fragmentList);
        }
        mvpGoodsFragmentTwo.setAdapter(adapter);
        tvPointsFragmentTwo.setSelected(true);
        tvPointsFragmentTwo.setTextColor(getActivity().getResources().getColor(R.color.theme));
        mvpGoodsFragmentTwo.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) ivIndicatorFragmentTwo.getLayoutParams();
                lp.leftMargin = (int) (position * width + positionOffset * width);
                ivIndicatorFragmentTwo.setLayoutParams(lp);
            }

            @Override
            public void onPageSelected(int position) {
                tvPointsFragmentTwo.setSelected(false);
                tvMFragmentTwo.setSelected(false);
                switch (position) {
                    case 0:
                        tvPointsFragmentTwo.setSelected(true);
                        tvPointsFragmentTwo.setTextColor(getActivity().getResources().getColor(R.color.theme));
                        tvMFragmentTwo.setTextColor(getActivity().getResources().getColor(R.color.black));
                        break;
                    case 1:
                        tvMFragmentTwo.setSelected(true);
                        tvMFragmentTwo.setTextColor(getActivity().getResources().getColor(R.color.theme));
                        tvPointsFragmentTwo.setTextColor(getActivity().getResources().getColor(R.color.black));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                logDebug("state:" + String.valueOf(state));
            }
        });
    }

    private void initGoalsGoods() {

    }

    private void initMGoods() {

    }

    @OnClick({R.id.tv_points_fragment_two, R.id.tv_m_fragment_two})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_points_fragment_two:
                mvpGoodsFragmentTwo.setCurrentItem(0);
                break;
            case R.id.tv_m_fragment_two:
                mvpGoodsFragmentTwo.setCurrentItem(1);
                break;
        }
    }

}
