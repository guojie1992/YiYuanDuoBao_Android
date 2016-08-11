package so.len.duobao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import so.len.duobao.R;
import so.len.duobao.customAdapter.GoodsGridViewAdapter;
import so.len.duobao.iPresenter.PointsGoodsPresenter;
import so.len.duobao.iView.IPointsGoodsView;

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
        for (int i = 0; i < 8; i++) {
            map = new HashMap<>();
            map.put("ivTitleItemGridviewGoods", R.drawable.title);
            map.put("tvTitleItemGridviewGoods", "IPhone 6s Plus 128GB");
            map.put("tvPriceItemGridviewGoods", "6199积分");
            goodsListData.add(map);
        }
//        logInfo(goodsListData.toString());
        goodsGridViewAdapter = new GoodsGridViewAdapter(getActivity(), goodsListData);
        gvFragmentGoodsPoint.setAdapter(goodsGridViewAdapter);
    }

}
