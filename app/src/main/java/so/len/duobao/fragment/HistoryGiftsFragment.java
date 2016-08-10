package so.len.duobao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
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
    private HistoryGiftsListViewAdapter adapter;
    private LinkedHashMap<String, List<String>> data;
    private HistoryGiftsPresenter historyGiftsPresenter;

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
        data = new LinkedHashMap<>();
        List<String> texts;

        texts = new ArrayList<>();
        texts.add("等额本息");
        texts.add("一次性还本付息");
        texts.add("每月还息到期还本");
        data.put("还款方式", texts);

        texts = new ArrayList<>();
        texts.add("1个月以下");
        texts.add("1-2个月");
        texts.add("3-4个月");
        texts.add("5-6个月");
        texts.add("6个月以上");
        data.put("借款期限", texts);

        texts = new ArrayList<>();
        texts.add("5万以下");
        texts.add("5-10万");
        texts.add("10-30万");
        texts.add("30-50万");
        texts.add("50万以上");
        data.put("借款金额", texts);

        if(adapter == null){
            adapter = new HistoryGiftsListViewAdapter(getContext(), data);
        }
        hlvHistorygiftsFragmentGiftsHistory.setAdapter(adapter);
    }
}
