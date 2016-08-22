package so.len.duobao.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import so.len.duobao.R;
import so.len.duobao.customView.TopMenuBar;
import so.len.duobao.mPresenter.ChangeRecommenderPresenter;
import so.len.duobao.mView.IChangeRecommenderView;

/**
 * Created by Chung on 2016/8/17.
 */
public class ChangeRecommenderActivity extends BaseActivity implements IChangeRecommenderView {
    @BindView(R.id.tmb_activity_change_recommender)
    TopMenuBar tmbActivityChangeRecommender;
    @BindView(R.id.et_activity_change_recommender)
    EditText etActivityChangeRecommender;

    private ChangeRecommenderPresenter changeRecommenderPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_recommender);
        ButterKnife.bind(this);
        tmbActivityChangeRecommender.setMenuTopPadding(statusHeight);
        control();
    }

    private void control() {
        changeRecommenderPresenter = new ChangeRecommenderPresenter(this);
        changeRecommenderPresenter.initView();
    }

    @Override
    public void initView() {

        tmbActivityChangeRecommender.setBackVisibility(View.VISIBLE);
        tmbActivityChangeRecommender.setBackSrc(R.mipmap.top_back);
        tmbActivityChangeRecommender.setTitleText("我的推荐人");
        tmbActivityChangeRecommender.setMenuVisibility(View.VISIBLE);
        tmbActivityChangeRecommender.setMenuSrc(R.mipmap.save);
        tmbActivityChangeRecommender.setOnBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tmbActivityChangeRecommender.setOnMenuClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast("保存");
                finish();
            }
        });
    }
}
