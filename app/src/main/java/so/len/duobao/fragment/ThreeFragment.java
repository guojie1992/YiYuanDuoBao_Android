package so.len.duobao.fragment;

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
import so.len.duobao.customView.SpeakerView;
import so.len.duobao.http.Options;
import so.len.duobao.http.VolleyHttp;
import so.len.duobao.iPresenter.ThreePresenter;
import so.len.duobao.iView.IThreeView;

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

    private ThreePresenter threePresenter;
    private VolleyHttp vh;
    private Options opt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three, null);
        ButterKnife.bind(this, view);
        control();
        return view;
    }

    private void control() {
        vh = VolleyHttp.getInstance();
        opt = new Options();
//        opt.defImage(R.mipmap.ic_launcher)
//                .errImage(R.mipmap.ic_launcher);
        threePresenter = new ThreePresenter(this);
        threePresenter.initPoint();
        threePresenter.initM();
        threePresenter.initSpeaker();
    }

    @Override
    public void initPoint() {
        vh.imageLoader("http://pic73.nipic.com/file/20150722/19795594_122255146861_2.jpg", ivPointFragmentThree, null);
    }

    @Override
    public void initM() {
        vh.imageLoader("http://pic73.nipic.com/file/20150722/19795594_122255146861_2.jpg", ivMFragmentThree, null);
    }

    @Override
    public void initSpeaker() {
        tvSpeakerFragmentThree.setText("恭喜用户chung567115中奖啦！恭喜用户zyq中奖啦！恭喜用户zyx中奖啦！");
        tvSpeakerFragmentThree.setSelected(true);
    }

    @OnClick({R.id.ll_point_fragment_three, R.id.ll_m_fragment_three})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_point_fragment_three:
                toast("point");
                break;
            case R.id.ll_m_fragment_three:
                toast("M");
                break;
        }
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
