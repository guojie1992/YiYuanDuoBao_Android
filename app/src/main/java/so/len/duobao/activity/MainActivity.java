package so.len.duobao.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import so.len.duobao.R;
import so.len.duobao.customAdapter.FragmentViewPagerAdapter;
import so.len.duobao.customView.BottomMenuItem;
import so.len.duobao.customView.FragmentViewPager;
import so.len.duobao.customView.TopMenuBar;
import so.len.duobao.fragment.FourFragment;
import so.len.duobao.fragment.FiveFragment;
import so.len.duobao.fragment.OneFragment;
import so.len.duobao.fragment.ThreeFragment;
import so.len.duobao.fragment.TwoFragment;
import so.len.duobao.iPresenter.MainPresenter;
import so.len.duobao.iView.IMainView;

/**
 * Created by Chung on 2016/8/3.
 */
public class MainActivity extends BaseActivity implements IMainView {
    @BindView(R.id.top_menu_bar)
    TopMenuBar topMenuBar;
    @BindView(R.id.menu_item_1)
    BottomMenuItem menuItem1;
    @BindView(R.id.menu_item_2)
    BottomMenuItem menuItem2;
    @BindView(R.id.menu_item_3)
    BottomMenuItem menuItem3;
    @BindView(R.id.menu_item_4)
    BottomMenuItem menuItem4;
    @BindView(R.id.menu_item_5)
    BottomMenuItem menuItem5;
    @BindView(R.id.vp_home)
    FragmentViewPager vpHome;

    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        control();
    }

    private void control() {
        mainPresenter = new MainPresenter(MainActivity.this);
        mainPresenter.setMenuTopPadding();
        mainPresenter.initFragmentViewPager();
    }

    @Override
    public void setMenuTopPadding() {
        topMenuBar.setMenuTopPadding(statusHeight);
    }

    @Override
    public void initFragmentViewPager() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new OneFragment());
        fragments.add(new TwoFragment());
        fragments.add(new ThreeFragment());
        fragments.add(new FourFragment());
        fragments.add(new FiveFragment());

        FragmentViewPagerAdapter adapter = new FragmentViewPagerAdapter(getSupportFragmentManager(), fragments);
        vpHome.setAdapter(adapter);
        vpHome.setScrollable(false);
        vpHome.setOffscreenPageLimit(5);
        vpHome.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                select(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        select(0);

        menuItem1.setMenuItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vpHome.setCurrentItem(0);
            }
        });
        menuItem2.setMenuItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vpHome.setCurrentItem(1);
            }
        });
        menuItem3.setMenuItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vpHome.setCurrentItem(2);
            }
        });
        menuItem4.setMenuItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vpHome.setCurrentItem(3);
            }
        });
        menuItem5.setMenuItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vpHome.setCurrentItem(4);
            }
        });
    }

    @Override
    public void select(int index) {
        menuItem1.setSelected(false);
        menuItem2.setSelected(false);
        menuItem3.setSelected(false);
        menuItem4.setSelected(false);
        menuItem5.setSelected(false);

        Resources res = getResources();
        switch (index) {
            case 0:
                menuItem1.setSelected(true);
                topMenuBar.setTitleText(res.getString(R.string.bottom_menu_item_1));
                topMenuBar.setVisibility(View.VISIBLE);
                topMenuBar.setTitleVisibility(View.VISIBLE);
                topMenuBar.setBackVisibility(View.INVISIBLE);
                topMenuBar.setMenuVisibility(View.INVISIBLE);
                topMenuBar.setAddVisibility(View.INVISIBLE);
                break;
            case 1:
                menuItem2.setSelected(true);
                topMenuBar.setTitleText(res.getString(R.string.bottom_menu_item_2));
                topMenuBar.setVisibility(View.VISIBLE);
                topMenuBar.setTitleVisibility(View.VISIBLE);
                topMenuBar.setBackVisibility(View.INVISIBLE);
                topMenuBar.setMenuVisibility(View.INVISIBLE);
                topMenuBar.setAddVisibility(View.INVISIBLE);
                break;
            case 2:
                menuItem3.setSelected(true);
                topMenuBar.setTitleText(res.getString(R.string.bottom_menu_item_3));
                topMenuBar.setVisibility(View.VISIBLE);
                topMenuBar.setTitleVisibility(View.VISIBLE);
                topMenuBar.setBackVisibility(View.INVISIBLE);
                topMenuBar.setMenuVisibility(View.INVISIBLE);
                topMenuBar.setAddVisibility(View.INVISIBLE);
                break;
            case 3:
                menuItem4.setSelected(true);
                topMenuBar.setTitleText(res.getString(R.string.bottom_menu_item_4));
                topMenuBar.setVisibility(View.VISIBLE);
                topMenuBar.setTitleVisibility(View.VISIBLE);
                topMenuBar.setBackVisibility(View.INVISIBLE);
                topMenuBar.setMenuVisibility(View.INVISIBLE);
                topMenuBar.setAddVisibility(View.INVISIBLE);
                break;
            case 4:
                menuItem5.setSelected(true);
                topMenuBar.setTitleText(res.getString(R.string.bottom_menu_item_5));
                topMenuBar.setVisibility(View.VISIBLE);
                topMenuBar.setTitleVisibility(View.VISIBLE);
                topMenuBar.setBackVisibility(View.INVISIBLE);
                topMenuBar.setMenuVisibility(View.INVISIBLE);
                topMenuBar.setAddVisibility(View.INVISIBLE);
                break;
        }
    }

}
