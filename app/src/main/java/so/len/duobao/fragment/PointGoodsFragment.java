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
import so.len.duobao.customAdapter.GoodsGridViewAdapter;
import so.len.duobao.mPresenter.PointsGoodsPresenter;
import so.len.duobao.mView.IPointsGoodsView;

/**
 * Created by Chung on 2016/8/9.
 */
public class PointGoodsFragment extends BaseFragment implements IPointsGoodsView {
    @BindView(R.id.gv_fragment_goods_point)
    GridView gvFragmentGoodsPoint;

    private PointsGoodsPresenter pointsGoodsPresenter;
    private List<Map<String, Object>> goodsListData;
    private HashMap<String, Object> map;
    private GoodsGridViewAdapter goodsGridViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goods_point, null);
        ButterKnife.bind(this, view);
        control();
        return view;
    }

    private void control() {
        pointsGoodsPresenter = new PointsGoodsPresenter(this);
        pointsGoodsPresenter.initView();
    }

    @Override
    public void initView() {
        initPointsGoodsList();
    }

    private void initPointsGoodsList() {
        goodsListData = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            map = new HashMap<>();
            map.put("ivTitleItemGridviewGoods", R.drawable.iphone);
            map.put("tvTitleItemGridviewGoods", "iPhone 6s Plus 128GB");
            map.put("tvPriceItemGridviewGoods", "6199积分");
            goodsListData.add(map);
        }
//        logInfo(goodsListData.toString());
        goodsGridViewAdapter = new GoodsGridViewAdapter(getActivity(), goodsListData);
        gvFragmentGoodsPoint.setAdapter(goodsGridViewAdapter);
        gvFragmentGoodsPoint.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), WebViewActivity.class);
                intent.putExtra(JS.H5_TITLE, "商品");
                intent.putExtra(JS.H5_URL, HTML.SHOP);
                intent.putExtra("TOP_RIGHT", WebViewActivity.TOP_RIGHT.no_right_top);
                startActivity(intent);
            }
        });
    }

}
