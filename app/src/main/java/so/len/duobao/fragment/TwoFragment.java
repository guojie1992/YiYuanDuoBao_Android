package so.len.duobao.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.orhanobut.logger.Logger;

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
import so.len.duobao.mPresenter.TwoPresenter;
import so.len.duobao.mView.ITwoView;

/**
 * Created by Chung on 2016/8/3.
 */
public class TwoFragment extends BaseFragment implements ITwoView {
    @BindView(R.id.iv_top_fragment_two)
    ImageView ivTopFragmentTwo;
    @BindView(R.id.gv_fragment_two)
    GridView gvFragmentTwo;

    private TwoPresenter twoPresenter;
    private Context context;
    private TwoBean twoBean;

    private List<Map<String, Object>> goodsListData;
    private HashMap<String, Object> map;
    private GoodsGridViewAdapter goodsGridViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, null);
        ButterKnife.bind(this, view);
        context = getActivity();
        control();
        return view;
    }

    private void control() {
        twoPresenter = new TwoPresenter(this, context);
        twoPresenter.initView();
    }

    @Override
    public void initView(final TwoBean twoBean) {
        this.twoBean = twoBean;

        if(twoBean != null && twoBean.getData() != null){
            goodsListData = new ArrayList<>();
            for (int i = 0; i < twoBean.getData().getGoods_list().size(); i++) {
                map = new HashMap<>();
                map.put("ivTitleItemGridviewGoods", SERVER.DOMAIN + twoBean.getData().getGoods_list().get(i).getPath());
                map.put("tvTitleItemGridviewGoods", twoBean.getData().getGoods_list().get(i).getTitle());
                map.put("tvPriceItemGridviewGoods", twoBean.getData().getGoods_list().get(i).getPrice());
                goodsListData.add(map);
            }
            goodsGridViewAdapter = new GoodsGridViewAdapter(getActivity(), goodsListData);
            gvFragmentTwo.setAdapter(goodsGridViewAdapter);
            gvFragmentTwo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), WebViewActivity.class);
                    intent.putExtra(JS.H5_TITLE, "商品");
                    intent.putExtra(JS.H5_URL, HTML.SHOP);
                    intent.putExtra("TOP_RIGHT", WebViewActivity.TOP_RIGHT.no_right_top);
                    intent.putExtra("isGoods", true);
                    intent.putExtra("goodsID", twoBean.getData().getGoods_list().get(position).getId());
                    Logger.d(twoBean.getData().getGoods_list().get(position).getId());
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void onResume() {
        twoPresenter.initView();
        super.onResume();
    }

}
