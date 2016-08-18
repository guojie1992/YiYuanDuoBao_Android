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
    @BindView(R.id.sv_speaker_fragment_one)
    SpeakerView svSpeakerFragmentOne;
    @BindView(R.id.tv_speaker_fragment_one)
    TextView tvSpeakerFragmentOne;
//    @BindView(R.id.srl_fragment_one)
//    SwipeRefreshLayout srlFragmentOne;

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
        onePresenter.initView();
    }

    @Override
    public void initView(OneBean oneBean) {
        initLoopViewPager(oneBean);
        initLotteryList(oneBean);
//        initRefresh();
    }

    private void initLoopViewPager(OneBean oneBean) {
        int padding = (int) getResources().getDimension(R.dimen.dp_2);

        ArrayList<String> pics = new ArrayList<>();
        for (OneDataPic pic : oneBean.getData().getPic()) {
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

    private void initLotteryList(OneBean oneBean) {
        tvSpeakerFragmentOne.setText("孙中奖啦！柳中奖啦！张中奖啦！江中奖啦！金中奖啦！景中奖啦！谢中奖啦！王中奖啦！！");
        tvSpeakerFragmentOne.setSelected(true);

        lotteryListData = new ArrayList<>();
        for (int i = 0; i < oneBean.getData().getList().size(); i++) {
            map = new HashMap<>();
            map.put("ivTitleItemListviewLottery", SERVER.DOMAIN + oneBean.getData().getList().get(i).getPic());
            map.put("tvUsernameItemListviewLottery", oneBean.getData().getList().get(i).getNickname());
            map.put("tvTimeItemListviewLottery", oneBean.getData().getList().get(i).getCreate_time());
            map.put("tvContentItemListviewLottery", oneBean.getData().getList().get(i).getContent());
            lotteryListData.add(map);
        }
//        logInfo(lotteryListData.toString());
        lotteryListViewAdapter = new LotteryListViewAdapter(getActivity(), lotteryListData);
        llvLotteryFragmentOne.setAdapter(lotteryListViewAdapter);
    }

//    private void initRefresh() {
//        srlFragmentOne.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                toast("refresh");
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        srlFragmentOne.setRefreshing(false);
//                    }
//                }, 2000);
//            }
//        });
//    }

    @Override
    public void onResume() {
        svSpeakerFragmentOne.startSpeaker(300);
        if (lvpFragmentOne != null)
            lvpFragmentOne.openLoopTimer();
        super.onResume();
    }

    @Override
    public void onPause() {
        svSpeakerFragmentOne.stopSpeaker();
        if (lvpFragmentOne != null)
            lvpFragmentOne.cancelLoopTimer();
        super.onPause();
    }

}
