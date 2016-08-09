package so.len.duobao.customAdapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import so.len.duobao.R;
import so.len.duobao.http.Options;
import so.len.duobao.http.VolleyHttp;

public class LoopViewPagerAdapter extends PagerAdapter {
    private List<View> viewList;

    public LoopViewPagerAdapter(Context context, String... us){
        List<String> urls=new ArrayList<>();
        for(String u:us){
            urls.add(u);
        }
        loopViewPagerAdapter(context,urls);
    }
    public LoopViewPagerAdapter(Context context, List<String> urls){
        loopViewPagerAdapter(context,urls);
    }
    private void loopViewPagerAdapter(Context context,List<String> urls){
        viewList=new ArrayList<>();
        VolleyHttp vh = VolleyHttp.getInstance();
        Options opt = new Options();
//        opt.defImage(R.mipmap.ic_launcher)
//                .errImage(R.mipmap.ic_launcher);
        onCreateView(context,vh,urls.get(urls.size()-1),opt);
        for(String url:urls){
            onCreateView(context,vh,url,opt);
        }
        onCreateView(context,vh,urls.get(0),opt);
    }
    private void onCreateView(Context context,VolleyHttp vh,String url,Options opt){
        ImageView view=new ImageView(context);
        view.setTag(url);
        view.setScaleType(ImageView.ScaleType.FIT_XY);
        vh.imageLoader(url,view,opt);
        viewList.add(view);
    }
    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position));
        return viewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view==o;
    }
}
