package so.len.duobao.customAdapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Chung on 2016/5/11.
 */
public class LaunchViewPagerAdapter extends PagerAdapter {
    private ArrayList<View> viewLists;

    public LaunchViewPagerAdapter(){
    }
    public LaunchViewPagerAdapter(ArrayList<View> viewLists){
        super();
        this.viewLists = viewLists;
    }

    @Override
    public int getCount() {
        return viewLists.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewLists.get(position));
        return viewLists.get(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewLists.get(position));
    }
}
