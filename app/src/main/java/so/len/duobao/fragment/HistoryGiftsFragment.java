package so.len.duobao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import so.len.duobao.R;

/**
 * Created by Chung on 2016/8/9.
 */
public class HistoryGiftsFragment extends BaseFragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gifts_history, null);

        return view;
    }
}
