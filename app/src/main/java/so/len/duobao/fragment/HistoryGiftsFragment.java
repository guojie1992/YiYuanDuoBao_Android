package so.len.duobao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import so.len.duobao.R;
import so.len.duobao.customAdapter.HistoryGiftsListViewAdapter;
import so.len.duobao.customView.HistoryGiftsListView;
import so.len.duobao.iPresenter.HistoryGiftsPresenter;
import so.len.duobao.iView.IHistoryGiftsView;

/**
 * Created by Chung on 2016/8/9.
 */
public class HistoryGiftsFragment extends BaseFragment implements IHistoryGiftsView {
    @BindView(R.id.hlv_historygifts_fragment_gifts_history)
    HistoryGiftsListView hlvHistorygiftsFragmentGiftsHistory;

    private HistoryGiftsPresenter historyGiftsPresenter;
    private HistoryGiftsListViewAdapter adapter;
    private Map<String, List<String>> data;
    private List<String> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gifts_history, null);
        ButterKnife.bind(this, view);
        control();
        return view;
    }

    private void control() {
        historyGiftsPresenter = new HistoryGiftsPresenter(this);
        historyGiftsPresenter.initView();
    }

    @Override
    public void initView() {
        data = new LinkedHashMap<String, List<String>>();

        for(int i=0;i<8;i++){
            list = new ArrayList<String>();
            list.add("chung567115抢到了1000元代金券");
            list.add("chung567115抢到了1000元代金券");
            list.add("chung567115抢到了1000元代金券");
            list.add("chung567115抢到了1000元代金券");
            list.add("chung567115抢到了1000元代金券");
            list.add("chung567115抢到了1000元代金券");
            data.put("2016/08/10 19:29 " + String.valueOf(i), list);
        }

//        logDebug(data.toString());
        if(adapter == null){
            adapter = new HistoryGiftsListViewAdapter(getActivity(), data);
        }
        hlvHistorygiftsFragmentGiftsHistory.setAdapter(adapter);
    }
}
