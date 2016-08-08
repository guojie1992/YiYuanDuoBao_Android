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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import so.len.duobao.App;
import so.len.duobao.R;
import so.len.duobao.activity.ForgetActivity;
import so.len.duobao.activity.LoginActivity;
import so.len.duobao.activity.RegisterActivity;
import so.len.duobao.bean.ScrollPics;
import so.len.duobao.customView.LoopViewPager;
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

    private OnePresenter onePresenter;
    private List<ImageView> dots;

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

    }
}
