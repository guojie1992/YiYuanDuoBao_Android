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
import so.len.duobao.bean.FourBean;
import so.len.duobao.customAdapter.TreasureGridViewAdapter;
import so.len.duobao.mPresenter.FourPresenter;
import so.len.duobao.mView.IFourView;

/**
 * Created by Chung on 2016/8/3.
 */
public class FourFragment extends BaseFragment implements IFourView {
    @BindView(R.id.gv_treasure_fragment_four)
    GridView gvTreasureFragmentFour;

    private Context context;
    private FourPresenter fourPresenter;
    private List<Map<String, Object>> treasureListData;
    private HashMap<String, Object> map;
    private TreasureGridViewAdapter treasureGridViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_four, null);
        ButterKnife.bind(this, view);
        context = getActivity();
        control();
        return view;
    }

    private void control() {
        fourPresenter = new FourPresenter(this, context);
        fourPresenter.initView();
    }

    @Override
    public void initView(FourBean fourBean) {
        treasureListData = new ArrayList<>();
        for (int i = 0; i < fourBean.getData().size(); i++) {
            map = new HashMap<>();
            map.put("ivTitleItemGridviewTreasure", SERVER.DOMAIN + fourBean.getData().get(i).getPath());
            map.put("tvTitleItemGridviewTreasure", fourBean.getData().get(i).getTitle());
            map.put("pvProgressItemGridviewTreasure", fourBean.getData().get(i).getProgess());
            map.put("tvAllItemGridviewTreasure", "总需:" + fourBean.getData().get(i).getPrice());
            map.put("tvNeedItemGridviewTreasure", "剩余:" + fourBean.getData().get(i).getNumber());
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
