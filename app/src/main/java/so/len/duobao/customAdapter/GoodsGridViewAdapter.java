package so.len.duobao.customAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import so.len.duobao.R;
import so.len.duobao.http.Options;
import so.len.duobao.http.VolleyHttp;

/**
 * Created by Chung on 2016/8/10.
 */
public class GoodsGridViewAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String, Object>> data;
    private LayoutInflater layoutInflater;

    public GoodsGridViewAdapter(Context context, List<Map<String, Object>> data) {
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
            convertView = layoutInflater.inflate(R.layout.item_gridview_goods, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Options opt = new Options();
        VolleyHttp.getInstance().imageLoader((String) data.get(position).get("ivTitleItemGridviewGoods"), viewHolder.ivTitleItemGridviewGoods, opt);
        viewHolder.tvTitleItemGridviewGoods.setText((CharSequence) data.get(position).get("tvTitleItemGridviewGoods"));
        viewHolder.tvPriceItemGridviewGoods.setText((CharSequence) data.get(position).get("tvPriceItemGridviewGoods"));

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv_title_item_gridview_goods)
        ImageView ivTitleItemGridviewGoods;
        @BindView(R.id.tv_title_item_gridview_goods)
        TextView tvTitleItemGridviewGoods;
        @BindView(R.id.tv_price_item_gridview_goods)
        TextView tvPriceItemGridviewGoods;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
