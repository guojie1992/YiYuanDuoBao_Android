package so.len.duobao.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import so.len.duobao.R;
import so.len.duobao.api.HTML;
import so.len.duobao.api.JS;
import so.len.duobao.customAdapter.FragmentViewPagerAdapter;
import so.len.duobao.customView.BottomMenuItem;
import so.len.duobao.customView.FragmentViewPager;
import so.len.duobao.customView.TopMenuBar;
import so.len.duobao.fragment.FourFragment;
import so.len.duobao.fragment.FiveFragment;
import so.len.duobao.fragment.OneFragment;
import so.len.duobao.fragment.ThreeFragment;
import so.len.duobao.fragment.TwoFragment;
import so.len.duobao.mPresenter.MainPresenter;
import so.len.duobao.mView.IMainView;

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

    public static boolean isForeground = false;
    private MainPresenter mainPresenter;
    private long backTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        topMenuBar.setMenuTopPadding(statusHeight);
        control();
    }

    private void control() {
        mainPresenter = new MainPresenter(MainActivity.this);
        mainPresenter.initView();
    }

    @Override
    public void initView() {

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

    private void select(int index) {
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
                topMenuBar.setBackVisibility(View.VISIBLE);
                topMenuBar.setBackSrc(R.mipmap.top_mine);
                topMenuBar.setMenuVisibility(View.INVISIBLE);
                topMenuBar.setOnBackClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this, MineActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            case 1:
                menuItem2.setSelected(true);
                topMenuBar.setTitleText(res.getString(R.string.bottom_menu_item_2));
                topMenuBar.setVisibility(View.VISIBLE);
                topMenuBar.setTitleVisibility(View.VISIBLE);
                topMenuBar.setBackVisibility(View.INVISIBLE);
                topMenuBar.setMenuVisibility(View.INVISIBLE);
                break;
            case 2:
                menuItem3.setSelected(true);
                topMenuBar.setTitleText(res.getString(R.string.bottom_menu_item_3));
                topMenuBar.setVisibility(View.VISIBLE);
                topMenuBar.setTitleVisibility(View.VISIBLE);
                topMenuBar.setBackVisibility(View.INVISIBLE);
                topMenuBar.setMenuVisibility(View.VISIBLE);
                topMenuBar.setMenuSrc(R.mipmap.top_gifts);
                topMenuBar.setOnMenuClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this, WebViewActivity.class);
                        intent.putExtra(JS.H5_TITLE, "我的抽奖");
                        intent.putExtra(JS.H5_URL, HTML.LOTTERY_RECORD);
                        intent.putExtra("TOP_RIGHT", WebViewActivity.TOP_RIGHT.no_right_top);
                        startActivity(intent);
                    }
                });
                break;
            case 3:
                menuItem4.setSelected(true);
                topMenuBar.setTitleText(res.getString(R.string.bottom_menu_item_4));
                topMenuBar.setVisibility(View.VISIBLE);
                topMenuBar.setTitleVisibility(View.VISIBLE);
                topMenuBar.setBackVisibility(View.INVISIBLE);
                topMenuBar.setMenuVisibility(View.VISIBLE);
                topMenuBar.setMenuSrc(R.mipmap.top_treasure);
                topMenuBar.setOnMenuClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this, WebViewActivity.class);
                        intent.putExtra(JS.H5_TITLE, "我的夺宝");
                        intent.putExtra(JS.H5_URL, HTML.TREASURE_RECORD);
                        intent.putExtra("TOP_RIGHT", WebViewActivity.TOP_RIGHT.no_right_top);
                        startActivity(intent);
                    }
                });
                break;
            case 4:
                menuItem5.setSelected(true);
                topMenuBar.setTitleText(res.getString(R.string.bottom_menu_item_5));
                topMenuBar.setVisibility(View.VISIBLE);
                topMenuBar.setTitleVisibility(View.VISIBLE);
                topMenuBar.setBackVisibility(View.INVISIBLE);
                topMenuBar.setMenuVisibility(View.INVISIBLE);
                break;
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long exitTime = System.currentTimeMillis();
            if (exitTime - backTime > 800) {//如果两次按键时间间隔大于800毫秒，则不退出
                toast("再按一次退出程序");
                backTime = exitTime;//更新firstTime
                return true;
            } else {//否则退出程序
                backTime = 0;
                System.exit(0);
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
    }


    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }

}
