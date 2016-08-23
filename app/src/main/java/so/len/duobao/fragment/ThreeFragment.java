package so.len.duobao.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import so.len.duobao.R;
import so.len.duobao.activity.WebViewActivity;
import so.len.duobao.api.HTML;
import so.len.duobao.api.JS;
import so.len.duobao.bean.ThreeBean;
import so.len.duobao.customView.SpeakerView;
import so.len.duobao.database.Config;
import so.len.duobao.mPresenter.ThreePresenter;
import so.len.duobao.mView.IThreeView;

/**
 * Created by Chung on 2016/8/3.
 */
public class ThreeFragment extends BaseFragment implements IThreeView {
    @BindView(R.id.tv_speaker_fragment_three)
    TextView tvSpeakerFragmentThree;
    @BindView(R.id.iv_point_fragment_three)
    ImageView ivPointFragmentThree;
    @BindView(R.id.iv_m_fragment_three)
    ImageView ivMFragmentThree;
    @BindView(R.id.sv_speaker_fragment_three)
    SpeakerView svSpeakerFragmentThree;

    private Context context;
    private ThreePresenter threePresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three, null);
        ButterKnife.bind(this, view);
        this.context = getActivity();
        control();
        return view;
    }

    private void control() {
        threePresenter = new ThreePresenter(this, context);
        threePresenter.initView();
    }

    @Override
    public void initView(ThreeBean threeBean) {
        tvSpeakerFragmentThree.setText(threeBean.getData());
        tvSpeakerFragmentThree.setSelected(true);
    }


    @OnClick({R.id.ll_point_fragment_three, R.id.ll_m_fragment_three})
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), WebViewActivity.class);
        switch (view.getId()) {
            case R.id.ll_point_fragment_three:
                intent.putExtra(JS.H5_TITLE, "积分抽奖");
                intent.putExtra(JS.H5_URL, HTML.LOTTERY_POINT);
                intent.putExtra("needPost", true);
                intent.putExtra("postData", "uid=" + Config.getInstance(getActivity()).getConfig("uid"));
                intent.putExtra("TOP_RIGHT", WebViewActivity.TOP_RIGHT.no_right_top);
                break;
            case R.id.ll_m_fragment_three:
                intent.putExtra(JS.H5_TITLE, "M币+积分抽奖");
                intent.putExtra(JS.H5_URL, HTML.LOTTERY_M);
                intent.putExtra("needPost", true);
                intent.putExtra("postData", "uid=" + Config.getInstance(getActivity()).getConfig("uid"));
                intent.putExtra("TOP_RIGHT", WebViewActivity.TOP_RIGHT.no_right_top);
                break;
        }
        startActivity(intent);
    }

    @Override
    public void onResume() {
        svSpeakerFragmentThree.startSpeaker(300);
        super.onResume();
    }

    @Override
    public void onPause() {
        svSpeakerFragmentThree.stopSpeaker();
        super.onPause();
    }

}
