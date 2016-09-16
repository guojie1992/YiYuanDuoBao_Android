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
import de.hdodenhof.circleimageview.CircleImageView;
import so.len.duobao.R;
import so.len.duobao.api.SERVER;
import so.len.duobao.http.Options;
import so.len.duobao.http.VolleyHttp;

/**
 * Created by Chung on 2016/8/9.
 */
public class LotteryListViewAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String, Object>> data;
    private LayoutInflater layoutInflater;

    public LotteryListViewAdapter(Context context, List<Map<String, Object>> data) {
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
            convertView = layoutInflater.inflate(R.layout.item_listview_lottery, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.ivTitleItemListviewLottery.setTag((String) data.get(position).get("ivTitleItemListviewLottery"));

        Options opt = new Options();
//        opt.defImage(R.mipmap.appicon);
//        opt.errImage(R.mipmap.appicon);

        String tag = (String) viewHolder.ivTitleItemListviewLottery.getTag();
        if (tag != null && tag.equals((String) data.get(position).get("ivTitleItemListviewLottery"))) {
            VolleyHttp.getInstance().imageLoader((String) data.get(position).get("ivTitleItemListviewLottery"), viewHolder.ivTitleItemListviewLottery, opt);
        }

        viewHolder.tvUsernameItemListviewLottery.setText((CharSequence) data.get(position).get("tvUsernameItemListviewLottery"));
        viewHolder.tvTimeItemListviewLottery.setText((CharSequence) data.get(position).get("tvTimeItemListviewLottery"));
        viewHolder.tvContentItemListviewLottery.setText((CharSequence) data.get(position).get("tvContentItemListviewLottery"));

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv_title_item_listview_lottery)
        CircleImageView ivTitleItemListviewLottery;
        @BindView(R.id.tv_username_item_listview_lottery)
        TextView tvUsernameItemListviewLottery;
        @BindView(R.id.tv_time_item_listview_lottery)
        TextView tvTimeItemListviewLottery;
        @BindView(R.id.tv_content_item_listview_lottery)
        TextView tvContentItemListviewLottery;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
