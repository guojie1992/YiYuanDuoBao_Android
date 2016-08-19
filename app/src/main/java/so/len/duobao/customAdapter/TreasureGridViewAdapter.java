package so.len.duobao.customAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import so.len.duobao.R;

/**
 * Created by Chung on 2016/8/9.
 */
public class TreasureGridViewAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String, Object>> data;
    private LayoutInflater layoutInflater;

    public TreasureGridViewAdapter(Context context, List<Map<String, Object>> data) {
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
            convertView = layoutInflater.inflate(R.layout.item_gridview_treasure, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.ivTitleItemGridviewTreasure.setImageResource((Integer) data.get(position).get("ivTitleItemGridviewTreasure"));
        viewHolder.tvTitleItemGridviewTreasure.setText((CharSequence) data.get(position).get("tvTitleItemGridviewTreasure"));
        viewHolder.pvProgressItemGridviewTreasure.setProgress((float) 0.5);//((float) data.get(position).get("pvProgressItemGridviewTreasure"));
        viewHolder.tvAllItemGridviewTreasure.setText((CharSequence) data.get(position).get("tvAllItemGridviewTreasure"));
        viewHolder.tvNeedItemGridviewTreasure.setText((CharSequence) data.get(position).get("tvNeedItemGridviewTreasure"));

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv_title_item_gridview_treasure)
        ImageView ivTitleItemGridviewTreasure;
        @BindView(R.id.tv_title_item_gridview_treasure)
        TextView tvTitleItemGridviewTreasure;
        @BindView(R.id.pv_progress_item_gridview_treasure)
        RoundCornerProgressBar pvProgressItemGridviewTreasure;
        @BindView(R.id.tv_all_item_gridview_treasure)
        TextView tvAllItemGridviewTreasure;
        @BindView(R.id.tv_need_item_gridview_treasure)
        TextView tvNeedItemGridviewTreasure;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
