package so.len.duobao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import butterknife.BindView;
import butterknife.ButterKnife;
import so.len.duobao.R;
import so.len.duobao.bean.FiveBean;
import so.len.duobao.customAdapter.HistoryGiftsListViewAdapter;
import so.len.duobao.customView.HistoryGiftsListView;
import so.len.duobao.mPresenter.HistoryGiftsPresenter;
import so.len.duobao.mView.IHistoryGiftsView;
import so.len.duobao.otto.AppBus;

/**
 * Created by Chung on 2016/8/9.
 */
public class HistoryGiftsFragment extends BaseFragment implements IHistoryGiftsView {
    @BindView(R.id.hlv_historygifts_fragment_gifts_history)
    HistoryGiftsListView hlvHistorygiftsFragmentGiftsHistory;

    private FiveBean fiveBean;
    private HistoryGiftsPresenter historyGiftsPresenter;
    private HistoryGiftsListViewAdapter historyGiftsListViewAdapter;
    private Map<String, List<String>> data;
    private List<String> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gifts_history, null);
        ButterKnife.bind(this, view);
//        control();
        return view;
    }

    private void control() {
        historyGiftsPresenter = new HistoryGiftsPresenter(this);
        historyGiftsPresenter.initView();
    }

    @Override
    public void initView() {
        data = new LinkedHashMap<String, List<String>>();

        for(int i=0;i<fiveBean.getHistory_list().size();i++){
            list = new ArrayList<String>();
            for(int j=0;j<fiveBean.getHistory_list().get(i).getData_list().size();j++){
                list.add(fiveBean.getHistory_list().get(i).getData_list().get(j).getContent());
            }
            data.put(fiveBean.getHistory_list().get(i).getData_time(), list);
        }

//        logDebug(data.toString());
        if(historyGiftsListViewAdapter == null){
            historyGiftsListViewAdapter = new HistoryGiftsListViewAdapter(getActivity(), data);
        }
        hlvHistorygiftsFragmentGiftsHistory.setAdapter(historyGiftsListViewAdapter);

        hlvHistorygiftsFragmentGiftsHistory.setFocusable(false);
    }

    @Override
    public void onResume() {
        AppBus.getInstance().register(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        AppBus.getInstance().unregister(this);
        super.onPause();
    }

    @Subscribe
    public void onFiveBean(FiveBean fiveBean){
        this.fiveBean = fiveBean;
        if(fiveBean.getHistory_list() != null){
            control();
        }
    }

    public void refresh(){
        historyGiftsListViewAdapter.refresh();
        for(int k=0;k<fiveBean.getHistory_list().size();k++){
            hlvHistorygiftsFragmentGiftsHistory.expandGroup(k);
            hlvHistorygiftsFragmentGiftsHistory.collapseGroup(k);
        }
    }

}
