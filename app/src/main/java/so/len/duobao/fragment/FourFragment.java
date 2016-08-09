package so.len.duobao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import so.len.duobao.R;
import so.len.duobao.iPresenter.FourPresenter;
import so.len.duobao.iView.IFourView;

/**
 * Created by Chung on 2016/8/3.
 */
public class FourFragment extends BaseFragment implements IFourView{


    private FourPresenter fourPresenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_four, null);


        control();
        return view;
    }

    private void control() {
        fourPresenter = new FourPresenter(this);
        fourPresenter.initTreasureList();
    }

    @Override
    public void initTreasureList() {

    }
}
