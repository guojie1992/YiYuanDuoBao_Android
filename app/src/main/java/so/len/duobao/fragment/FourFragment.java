package so.len.duobao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import so.len.duobao.R;
import so.len.duobao.activity.WebViewActivity;
import so.len.duobao.api.HTML;
import so.len.duobao.api.JS;
import so.len.duobao.customAdapter.TreasureGridViewAdapter;
import so.len.duobao.mPresenter.FourPresenter;
import so.len.duobao.mView.IFourView;

/**
 * Created by Chung on 2016/8/3.
 */
public class FourFragment extends BaseFragment implements IFourView {
    @BindView(R.id.gv_treasure_fragment_four)
    GridView gvTreasureFragmentFour;

    private FourPresenter fourPresenter;
    private List<Map<String, Object>> treasureListData;
    private HashMap<String, Object> map;
    private TreasureGridViewAdapter treasureGridViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_four, null);
        ButterKnife.bind(this, view);

        control();
        return view;
    }

    private void control() {
        fourPresenter = new FourPresenter(this);
        fourPresenter.initView();
    }

    @Override
    public void initView() {
        initTreasureList();
    }

    private void initTreasureList() {
        treasureListData = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            map = new HashMap<>();
            map.put("ivTitleItemGridviewTreasure", R.drawable.iphone);
            map.put("tvTitleItemGridviewTreasure", "IPhone 6s Plus 128GB");
            map.put("pvProgressItemGridviewTreasure", 0.8);
            map.put("tvAllItemGridviewTreasure", "总需:2222");
            map.put("tvNeedItemGridviewTreasure", "剩余:1111");
            treasureListData.add(map);
        }
//        logInfo(treasureListData.toString());
        treasureGridViewAdapter = new TreasureGridViewAdapter(getActivity(), treasureListData);
        gvTreasureFragmentFour.setAdapter(treasureGridViewAdapter);
        gvTreasureFragmentFour.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), WebViewActivity.class);
                intent.putExtra(JS.H5_TITLE, "一元夺宝");
                intent.putExtra(JS.H5_URL, HTML.TREASURE);
                intent.putExtra("TOP_RIGHT", WebViewActivity.TOP_RIGHT.no_right_top);
                startActivity(intent);
            }
        });
    }

}
