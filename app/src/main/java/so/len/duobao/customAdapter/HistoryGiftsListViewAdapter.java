package so.len.duobao.customAdapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import so.len.duobao.R;

/**
 * Created by Chung on 2016/8/10.
 */
public class HistoryGiftsListViewAdapter extends BaseExpandableListAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<String> parentData;
    private Map<String, List<String>> data;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            notifyDataSetChanged();
            super.handleMessage(msg);
        }
    };
    public void refresh() {
        handler.sendMessage(new Message());
    }
    public HistoryGiftsListViewAdapter(Context context, Map<String, List<String>> data) {
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
        this.parentData = new ArrayList<>();
        Set<String> keys = data.keySet();
        for (String key : keys) {
            parentData.add(key);
        }
    }

    @Override
    public int getGroupCount() {
        return parentData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return data.get(parentData.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return parentData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return data.get(parentData.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ParentViewHolder parentViewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_listview_historygifts_parent, null);
            parentViewHolder = new ParentViewHolder(convertView);
            convertView.setTag(parentViewHolder);
        } else {
            parentViewHolder = (ParentViewHolder) convertView.getTag();
        }
        parentViewHolder.tvItemListviewHistorygiftsParent.setText(parentData.get(groupPosition));
        if (isExpanded) {
            parentViewHolder.ivItemListviewHistorygiftsParent.setImageResource(R.mipmap.up_arrow);
        } else {
            parentViewHolder.ivItemListviewHistorygiftsParent.setImageResource(R.mipmap.down_arrow);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_listview_historygifts_child, null);
            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        childViewHolder.tvItemListviewHistorygiftsChild.setText(data.get(parentData.get(groupPosition)).get(childPosition));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    static class ParentViewHolder {
        @BindView(R.id.iv_item_listview_historygifts_parent)
        ImageView ivItemListviewHistorygiftsParent;
        @BindView(R.id.tv_item_listview_historygifts_parent)
        TextView tvItemListviewHistorygiftsParent;

        ParentViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ChildViewHolder {
        @BindView(R.id.tv_item_listview_historygifts_child)
        TextView tvItemListviewHistorygiftsChild;

        ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
