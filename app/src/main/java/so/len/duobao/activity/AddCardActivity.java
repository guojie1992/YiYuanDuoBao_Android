package so.len.duobao.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import so.len.duobao.R;
import so.len.duobao.customView.TopMenuBar;
import so.len.duobao.mPresenter.AddCardPresenter;
import so.len.duobao.mView.IAddCardView;

/**
 * Created by Chung on 2016/8/17.
 */
public class AddCardActivity extends BaseActivity implements IAddCardView {
    @BindView(R.id.tmb_activity_add_card)
    TopMenuBar tmbActivityAddCard;
    @BindView(R.id.et_owner_activity_add_card)
    EditText etOwnerActivityAddCard;
    @BindView(R.id.et_bank_activity_add_card)
    EditText etBankActivityAddCard;
    @BindView(R.id.et_num_activity_add_card)
    EditText etNumActivityAddCard;

    private AddCardPresenter addCardPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        ButterKnife.bind(this);
        control();
    }

    private void control() {
        addCardPresenter = new AddCardPresenter(this);
        addCardPresenter.initView();
    }

    @Override
    public void initView() {
        tmbActivityAddCard.setMenuTopPadding(statusHeight);
        tmbActivityAddCard.setBackVisibility(View.VISIBLE);
        tmbActivityAddCard.setTitleVisibility(View.VISIBLE);
        tmbActivityAddCard.setMenuVisibility(View.VISIBLE);
        tmbActivityAddCard.setBackSrc(R.mipmap.top_back);
        tmbActivityAddCard.setTitleText("添加银行卡");
        tmbActivityAddCard.setMenuSrc(R.mipmap.save);
        tmbActivityAddCard.setOnBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tmbActivityAddCard.setOnMenuClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast("save");
                finish();
            }
        });
    }
}
