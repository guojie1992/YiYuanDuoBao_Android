package so.len.duobao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import so.len.duobao.R;
import so.len.duobao.customAdapter.LotteryListViewAdapter;
import so.len.duobao.customAdapter.MyGiftsListViewAdapter;
import so.len.duobao.customView.MyGiftsListView;
import so.len.duobao.iPresenter.MyGiftsPresenter;
import so.len.duobao.iView.IMyGiftsView;

/**
 * Created by Chung on 2016/8/9.
 */
public class MyGiftsFragment extends BaseFragment implements IMyGiftsView {
    @BindView(R.id.mlv_mygifts_fragment_gifts_my)
    MyGiftsListView mlvMygiftsFragmentGiftsMy;

    private MyGiftsPresenter myGiftsPresenter;
    private List<Map<String, Object>> myGiftsListData;
    private HashMap<String, Object> map;
    private MyGiftsListViewAdapter myGiftsListViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gifts_my, null);
        ButterKnife.bind(this, view);
        control();
        return view;
    }

    private void control() {
        myGiftsPresenter = new MyGiftsPresenter(this);
        myGiftsPresenter.initView();
    }

    @Override
    public void initView() {
        initmyGiftsList();
    }

    private void initmyGiftsList() {
        myGiftsListData = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            map = new HashMap<>();
            map.put("tvMoneyItemListviewMygifts", "100元代金券");
            map.put("tvTimeItemListviewMygifts", "2016/08/09 15:31");
            myGiftsListData.add(map);
        }
//        logInfo(myGiftsListData.toString());
        myGiftsListViewAdapter = new MyGiftsListViewAdapter(getActivity(), myGiftsListData);
        mlvMygiftsFragmentGiftsMy.setAdapter(myGiftsListViewAdapter);
    }
}
