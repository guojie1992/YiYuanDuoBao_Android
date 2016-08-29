package so.len.duobao.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import so.len.duobao.R;
import so.len.duobao.api.SERVER;
import so.len.duobao.bean.OneBean;
import so.len.duobao.bean.OneDataList;
import so.len.duobao.bean.OneDataPic;
import so.len.duobao.customAdapter.LotteryListViewAdapter;
import so.len.duobao.customView.LoopViewPager;
import so.len.duobao.customView.LotteryListView;
import so.len.duobao.customView.SpeakerView;
import so.len.duobao.mPresenter.OnePresenter;
import so.len.duobao.mView.IOneView;

/**
 * Created by Chung on 2016/8/3.
 */
public class OneFragment extends BaseFragment implements IOneView {
    @BindView(R.id.lvp_fragment_one)
    LoopViewPager lvpFragmentOne;
    @BindView(R.id.ll_dot_fragment_one)
    LinearLayout llDotFragmentOne;
    @BindView(R.id.llv_lottery_fragment_one)
    LotteryListView llvLotteryFragmentOne;
    @BindView(R.id.iv_head_fragment_one)
    ImageView ivHeadFragmentOne;
    @BindView(R.id.tv_speaker_fragment_one)
    TextView tvSpeakerFragmentOne;

    private OnePresenter onePresenter;
    private Context context;
    private List<ImageView> dots;
    private List<Map<String, Object>> lotteryListData;
    private HashMap<String, Object> map;
    private LotteryListViewAdapter lotteryListViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, null);
        ButterKnife.bind(this, view);
        context = getActivity();
        control();
        return view;
    }

    private void control() {
        onePresenter = new OnePresenter(this, context);
        onePresenter.initView(false);
    }

    @Override
    public void initView(OneBean oneBean, boolean isRefresh) {
        if(!isRefresh){
            initLoopViewPager(oneBean.getData().getPic());
        }
        initLotteryList(oneBean.getData().getList());
    }

    private void initLoopViewPager(List<OneDataPic> oneDataPic) {
        int padding = (int) getResources().getDimension(R.dimen.dp_2);

        ArrayList<String> pics = new ArrayList<>();
        for (OneDataPic pic : oneDataPic) {
            pics.add(SERVER.DOMAIN + pic.getPic());
        }
        lvpFragmentOne.addLoopImageUrl(pics);
        lvpFragmentOne.setLoopTimer(3000);
        lvpFragmentOne.setOnLoopPagerChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

            private void selected(int position) {
                for (ImageView imageView : dots) {
                    imageView.setSelected(false);
                }
                dots.get(position).setSelected(true);
            }
        });
        dots = new ArrayList<>();
        for (int i = 0; i < pics.size(); i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(R.drawable.selector_dot);
            dots.add(imageView);
            llDotFragmentOne.addView(imageView);
            imageView.setPadding(padding, padding, padding, padding);
        }
        dots.get(0).setSelected(true);
    }

    private void initLotteryList(List<OneDataList> oneDataList) {
        tvSpeakerFragmentOne.setText("中奖名单");
//        tvSpeakerFragmentOne.setSelected(true);

        lotteryListData = new ArrayList<>();
        for (int i = 0; i < oneDataList.size(); i++) {
            map = new HashMap<>();
            map.put("ivTitleItemListviewLottery", SERVER.DOMAIN + oneDataList.get(i).getPic());
            map.put("tvUsernameItemListviewLottery", oneDataList.get(i).getNickname());
            map.put("tvTimeItemListviewLottery", oneDataList.get(i).getCreate_time());
            map.put("tvContentItemListviewLottery", oneDataList.get(i).getContent());
            lotteryListData.add(map);
        }
        lotteryListViewAdapter = new LotteryListViewAdapter(getActivity(), lotteryListData);
        llvLotteryFragmentOne.setAdapter(lotteryListViewAdapter);
    }

    @Override
    public void onResume() {
        onePresenter.initView(true);
        if (lvpFragmentOne != null){
            lvpFragmentOne.openLoopTimer();
        }
        super.onResume();
    }

    @Override
    public void onPause() {
        if (lvpFragmentOne != null){
            lvpFragmentOne.cancelLoopTimer();
        }
        super.onPause();
    }

}
