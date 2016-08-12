package so.len.duobao.fragment;

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
import so.len.duobao.customAdapter.GoodsGridViewAdapter;
import so.len.duobao.iPresenter.MGoodsPresenter;
import so.len.duobao.iPresenter.PointsGoodsPresenter;
import so.len.duobao.iView.IMGoodsView;

/**
 * Created by Chung on 2016/8/9.
 */
public class MGoodsFragment extends BaseFragment implements IMGoodsView {
    @BindView(R.id.gv_fragment_goods_m)
    GridView gvFragmentGoodsM;

    private MGoodsPresenter mGoodsPresenter;
    private List<Map<String, Object>> goodsListData;
    private HashMap<String, Object> map;
    private GoodsGridViewAdapter goodsGridViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goods_m, null);
        ButterKnife.bind(this, view);
        control();
        return view;
    }
    private void control() {
        mGoodsPresenter = new MGoodsPresenter(this);
        mGoodsPresenter.initView();
    }

    @Override
    public void initView() {
        initMGoodsList();
    }

    private void initMGoodsList() {
        goodsListData = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            map = new HashMap<>();
            map.put("ivTitleItemGridviewGoods", R.drawable.iphone);
            map.put("tvTitleItemGridviewGoods", "IPhone 6s Plus 128GB");
            map.put("tvPriceItemGridviewGoods", "6199M币+1000积分");
            goodsListData.add(map);
        }
//        logInfo(goodsListData.toString());
        goodsGridViewAdapter = new GoodsGridViewAdapter(getActivity(), goodsListData);
        gvFragmentGoodsM.setAdapter(goodsGridViewAdapter);
        gvFragmentGoodsM.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                toast(String.valueOf(position));
            }
        });
    }
}
