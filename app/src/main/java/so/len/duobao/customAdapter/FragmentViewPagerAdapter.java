package so.len.duobao.customAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chung on 2016/8/3.
 */
public class FragmentViewPagerAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> fragmentList;

    public FragmentViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList=fragmentList;
    }
    public FragmentViewPagerAdapter(FragmentManager fm, Fragment... fragments) {
        super(fm);
        fragmentList= new ArrayList<>();
        for(Fragment f:fragments){
            fragmentList.add(f);
        }
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
