package so.len.duobao.customAdapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import so.len.duobao.R;
import so.len.duobao.customView.ExpandLinearLayout;

/**
 * Created by Chung on 2016/8/10.
 */
public class HistoryGiftsListViewAdapter extends BaseAdapter {
    private final Context context;
    private final Map<String, String> map;
    private final Map<String, List<String>> data;
    private final List<String> keyList;
    private Map<Integer, Integer> args = new LinkedHashMap<>();

    public HistoryGiftsListViewAdapter(Context context, Map<String, List<String>> data) {
        this.context = context;
        this.data = data;
        this.map = new LinkedHashMap<>();
        this.keyList = new ArrayList<>();
        Set<String> keys = data.keySet();
        for (String key : keys) {
            map.put(key, data.get(key).get(0) + ".1");
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
            convertView = new ExpandLinearLayout(context);
        }
        ((ExpandLinearLayout) convertView).setScreenArrow(R.mipmap.top_arrow);
        ((ExpandLinearLayout) convertView).setOnScreenItemListener(listener);
        ((ExpandLinearLayout) convertView).setTitleText(keyList.get(position));
        String info = map.get(keyList.get(position));
        String text = info.substring(0, info.lastIndexOf("."));
        boolean isUp;
        if (info.endsWith(".1"))
            isUp = true;
        else
            isUp = false;
        ((ExpandLinearLayout) convertView).addItem(position, data.get(keyList.get(position)), text, isUp);

        return convertView;
    }

    private ExpandLinearLayout.OnItemListener listener = new ExpandLinearLayout.OnItemListener() {
        @Override
        public void onItem(int position, String text, boolean isUp) {
            if (isUp) {
                map.put(keyList.get(position), text + ".1");
            } else {
                map.put(keyList.get(position), text + ".0");
            }
        }
    };

}
