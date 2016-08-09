package so.len.duobao.fragment;

import android.content.Intent;
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
import butterknife.OnClick;
import so.len.duobao.R;
import so.len.duobao.activity.ForgetActivity;
import so.len.duobao.activity.LoginActivity;
import so.len.duobao.activity.RegisterActivity;
import so.len.duobao.customAdapter.LotteryListViewAdapter;
import so.len.duobao.customView.LoopViewPager;
import so.len.duobao.customView.LotteryListView;
import so.len.duobao.customView.SpeakerView;
import so.len.duobao.iPresenter.OnePresenter;
import so.len.duobao.iView.IOneView;

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

    private OnePresenter onePresenter;
    private List<ImageView> dots;
    private List<Map<String, Object>> lotteryListData;
    private HashMap<String, Object> map;
    private LotteryListViewAdapter lotteryListViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, null);
        ButterKnife.bind(this, view);

        control();

        return view;
    }

    private void control() {
        onePresenter = new OnePresenter(this);
        onePresenter.initLoopViewPager();
        onePresenter.initLotteryList();
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btn1:
                intent.setClass(getActivity(), RegisterActivity.class);
                break;
            case R.id.btn2:
                intent.setClass(getActivity(), LoginActivity.class);
                break;
            case R.id.btn3:
                intent.setClass(getActivity(), ForgetActivity.class);
                break;
        }
        startActivity(intent);
    }

    @Override
    public void initLoopViewPager() {
        int padding = (int) getResources().getDimension(R.dimen.dp_2);

        ArrayList<String> pics = new ArrayList<>();
//        for (ScrollPics pic : App.BASEBEAN.getScrollPicsList()) {
//            pics.add(ServerInterface.SERVER + pic.getPic());
        pics.add("http://pic73.nipic.com/file/20150722/19795594_122255146861_2.jpg");
        pics.add("http://pic46.nipic.com/20140819/9051238_102048503204_2.jpg");
        pics.add("http://pic37.nipic.com/20140117/9301848_092945681365_2.jpg");
//        }
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

    @Override
    public void initLotteryList() {
        tvSpeakerFragmentOne.setText("孙中奖啦！柳中奖啦！张中奖啦！江中奖啦！金中奖啦！景中奖啦！谢中奖啦！王中奖啦！！");
        tvSpeakerFragmentOne.setSelected(true);

        lotteryListData = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            map = new HashMap<>();
            map.put("ivTitleItemListviewLottery", R.drawable.title);
            map.put("tvUsernameItemListviewLottery", "username");
            map.put("tvTimeItemListviewLottery", "2016/08/09 15:31");
            map.put("tvContentItemListviewLottery", "中奖啦中奖啦中奖啦中奖啦中奖啦中奖啦中奖啦");
            lotteryListData.add(map);
        }
        logInfo(lotteryListData.toString());
        lotteryListViewAdapter = new LotteryListViewAdapter(getActivity(), lotteryListData);
        llvLotteryFragmentOne.setAdapter(lotteryListViewAdapter);
    }

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
