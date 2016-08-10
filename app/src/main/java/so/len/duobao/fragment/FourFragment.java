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
import so.len.duobao.customAdapter.TreasureGridViewAdapter;
import so.len.duobao.iPresenter.FourPresenter;
import so.len.duobao.iView.IFourView;

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
        for (int i = 0; i < 8; i++) {
            map = new HashMap<>();
            map.put("ivTitleItemGridviewTreasure", R.drawable.title);
            map.put("tvTitleItemGridviewTreasure", "IPhone 6s Plus 128GB");
            map.put("pvProgressItemGridviewTreasure", 0.8);
            map.put("tvAllItemGridviewTreasure", "总需:2222");
            map.put("tvNeedItemGridviewTreasure", "剩余:1111");
            treasureListData.add(map);
        }
        logInfo(treasureListData.toString());
        treasureGridViewAdapter = new TreasureGridViewAdapter(getActivity(), treasureListData);
        gvTreasureFragmentFour.setAdapter(treasureGridViewAdapter);
    }

}
