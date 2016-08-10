package so.len.duobao.customAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import so.len.duobao.R;

/**
 * Created by Chung on 2016/8/10.
 */
public class MyGiftsListViewAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String, Object>> data;
    private LayoutInflater layoutInflater;

    public MyGiftsListViewAdapter(Context context, List<Map<String, Object>> data) {
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
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
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_listview_mygifts, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvMoneyItemListviewMygifts.setText((CharSequence) data.get(position).get("tvMoneyItemListviewMygifts"));
        viewHolder.tvTimeItemListviewMygifts.setText((CharSequence) data.get(position).get("tvTimeItemListviewMygifts"));

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_money_item_listview_mygifts)
        TextView tvMoneyItemListviewMygifts;
        @BindView(R.id.tv_time_item_listview_mygifts)
        TextView tvTimeItemListviewMygifts;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
