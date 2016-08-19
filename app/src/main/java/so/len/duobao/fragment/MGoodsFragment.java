package so.len.duobao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.orhanobut.logger.Logger;
import com.squareup.otto.Subscribe;

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
import so.len.duobao.api.SERVER;
import so.len.duobao.bean.TwoBean;
import so.len.duobao.customAdapter.GoodsGridViewAdapter;
import so.len.duobao.mPresenter.MGoodsPresenter;
import so.len.duobao.mView.IMGoodsView;
import so.len.duobao.otto.AppBus;

/**
 * Created by Chung on 2016/8/9.
 */
public class MGoodsFragment extends BaseFragment implements IMGoodsView {
    @BindView(R.id.gv_fragment_goods_m)
    GridView gvFragmentGoodsM;

    private TwoBean twoBean;
    private MGoodsPresenter mGoodsPresenter;
    private List<Map<String, Object>> goodsListData;
    private HashMap<String, Object> map;
    private GoodsGridViewAdapter goodsGridViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goods_m, null);
        ButterKnife.bind(this, view);
//        control();
        return view;
    }
    private void control() {
        mGoodsPresenter = new MGoodsPresenter(this);
        mGoodsPresenter.initView();
    }

    @Override
    public void initView() {
        goodsListData = new ArrayList<>();
        for (int i = 0; i < twoBean.getData().getMoney_goods_list().size(); i++) {
            map = new HashMap<>();
            map.put("ivTitleItemGridviewGoods", SERVER.DOMAIN + twoBean.getData().getMoney_goods_list().get(i).getPath());
            map.put("tvTitleItemGridviewGoods", twoBean.getData().getMoney_goods_list().get(i).getTitle());
            map.put("tvPriceItemGridviewGoods", twoBean.getData().getMoney_goods_list().get(i).getPrice() + "M币+" + twoBean.getData().getMoney_goods_list().get(i).getIntegral() + "积分");
            goodsListData.add(map);
        }
//        logInfo(goodsListData.toString());
        goodsGridViewAdapter = new GoodsGridViewAdapter(getActivity(), goodsListData);
        gvFragmentGoodsM.setAdapter(goodsGridViewAdapter);
        gvFragmentGoodsM.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
    public void onTwoBean(TwoBean twoBean){
        this.twoBean = twoBean;
        control();
    }
}
