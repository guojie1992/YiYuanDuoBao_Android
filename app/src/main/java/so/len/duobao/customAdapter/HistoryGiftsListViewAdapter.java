package so.len.duobao.customAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import so.len.duobao.R;
import so.len.duobao.customView.ExpandLinearLayout;

/**
 * Created by Chung on 2016/8/10.
 */
public class HistoryGiftsListViewAdapter extends BaseAdapter {
    private Context context;
    private LinkedHashMap<String, List<String>> data;
    private List<String> keyList;

    private ExpandLinearLayout expandLinearLayout;

    public HistoryGiftsListViewAdapter(Context context, LinkedHashMap<String, List<String>> data) {
        this.context = context;
        this.data = data;
        this.keyList = new ArrayList<>();
        Set<String> keys = data.keySet();
        for (String key : keys) {
            Logger.i("HistoryGiftsListViewAdapter:"+key);
            keyList.add(key);
        }
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            expandLinearLayout = new ExpandLinearLayout(context);
            convertView = expandLinearLayout;
            convertView.setTag(expandLinearLayout);
        }else {
            expandLinearLayout = (ExpandLinearLayout) convertView.getTag();
        }
        expandLinearLayout.setArrow(R.mipmap.top_arrow);
        expandLinearLayout.setTitleText(keyList.get(position));
        expandLinearLayout.addItem(data.get(keyList.get(position)));

        return expandLinearLayout;
    }

}
