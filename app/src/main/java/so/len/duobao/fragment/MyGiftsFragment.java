package so.len.duobao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import butterknife.BindView;
import butterknife.ButterKnife;
import so.len.duobao.R;
import so.len.duobao.bean.FiveBean;
import so.len.duobao.customAdapter.MyGiftsListViewAdapter;
import so.len.duobao.customView.MyGiftsListView;
import so.len.duobao.mPresenter.MyGiftsPresenter;
import so.len.duobao.mView.IMyGiftsView;
import so.len.duobao.otto.AppBus;

/**
 * Created by Chung on 2016/8/9.
 */
public class MyGiftsFragment extends BaseFragment implements IMyGiftsView {
    @BindView(R.id.mlv_mygifts_fragment_gifts_my)
    MyGiftsListView mlvMygiftsFragmentGiftsMy;

    private FiveBean fiveBean;
    private MyGiftsPresenter myGiftsPresenter;
    private List<Map<String, Object>> myGiftsListData;
    private HashMap<String, Object> map;
    private MyGiftsListViewAdapter myGiftsListViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gifts_my, null);
        ButterKnife.bind(this, view);
//        control();
        return view;
    }

    private void control() {
        myGiftsPresenter = new MyGiftsPresenter(this);
        myGiftsPresenter.initView();
    }

    @Override
    public void initView() {
        myGiftsListData = new ArrayList<>();
        for (int i = 0; i < fiveBean.getMy_list().size(); i++) {
            map = new HashMap<>();
            map.put("tvMoneyItemListviewMygifts", fiveBean.getMy_list().get(i).getRob_name());
            map.put("tvTimeItemListviewMygifts", fiveBean.getMy_list().get(i).getRob_time());
            myGiftsListData.add(map);
        }
        myGiftsListViewAdapter = new MyGiftsListViewAdapter(getActivity(), myGiftsListData);
        mlvMygiftsFragmentGiftsMy.setAdapter(myGiftsListViewAdapter);

        mlvMygiftsFragmentGiftsMy.setFocusable(false);
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
        if(fiveBean.getMy_list() != null){
            control();
        }
    }

}
