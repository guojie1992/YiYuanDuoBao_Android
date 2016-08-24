package so.len.duobao.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
    @BindView(R.id.tv_activity_change_recommender)
    TextView tvActivityChangeRecommender;
    @BindView(R.id.v)
    View v;

    private Context context;
    private ChangeRecommenderPresenter changeRecommenderPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_recommender);
        ButterKnife.bind(this);
        context = ChangeRecommenderActivity.this;
        tmbActivityChangeRecommender.setMenuTopPadding(statusHeight);
        control();
    }

    private void control() {
        changeRecommenderPresenter = new ChangeRecommenderPresenter(this, context);
        changeRecommenderPresenter.initView();
    }

    @Override
    public void initChangeableView() {
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
                changeRecommenderPresenter.setRecommender();
                finish();
            }
        });

        etActivityChangeRecommender.setVisibility(View.VISIBLE);
        v.setVisibility(View.VISIBLE);
        tvActivityChangeRecommender.setVisibility(View.GONE);
    }

    @Override
    public void initNoChangeView(String pid) {
        tmbActivityChangeRecommender.setBackVisibility(View.VISIBLE);
        tmbActivityChangeRecommender.setBackSrc(R.mipmap.top_back);
        tmbActivityChangeRecommender.setTitleText("我的推荐人");
        tmbActivityChangeRecommender.setMenuVisibility(View.INVISIBLE);
        tmbActivityChangeRecommender.setOnBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        etActivityChangeRecommender.setVisibility(View.GONE);
        v.setVisibility(View.GONE);
        tvActivityChangeRecommender.setVisibility(View.VISIBLE);
        if(pid.equals("0")){
            tvActivityChangeRecommender.setText("暂无推荐人");
        } else {
            tvActivityChangeRecommender.setText("推荐人ID:" + pid);
        }
    }

    @Override
    public String getRecommenderID() {
        return etActivityChangeRecommender.getText().toString().trim();
    }
}
