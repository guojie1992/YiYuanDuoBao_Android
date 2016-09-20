package so.len.duobao.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.OptionPicker;
import so.len.duobao.R;
import so.len.duobao.bean.BankListBean;
import so.len.duobao.customView.TopMenuBar;
import so.len.duobao.mPresenter.AddCardPresenter;
import so.len.duobao.mView.IAddCardView;
import so.len.duobao.utils.CommonUtils;

/**
 * Created by Chung on 2016/8/17.
 */
public class AddCardActivity extends BaseActivity implements IAddCardView {
    @BindView(R.id.tmb_activity_add_card)
    TopMenuBar tmbActivityAddCard;
    @BindView(R.id.et_owner_activity_add_card)
    EditText etOwnerActivityAddCard;
    @BindView(R.id.et_num_activity_add_card)
    EditText etNumActivityAddCard;
    @BindView(R.id.op_bank_activity_add_card)
    TextView opBankActivityAddCard;
    @BindView(R.id.et_addr_activity_add_card)
    EditText etAddrActivityAddCard;
    //    @BindView(R.id.sp_bank_activity_add_card)
//    Spinner spBankActivityAddCard;

    private Context context;
    private AddCardPresenter addCardPresenter;
    private ArrayList<String> dataList;
    //    private ArrayAdapter<String> arrayAdapter;
    private String bankID = "";
    private OptionPicker picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        ButterKnife.bind(this);
        context = AddCardActivity.this;
        tmbActivityAddCard.setMenuTopPadding(statusHeight);
        control();
    }

    private void control() {
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
                if (getUsername().isEmpty() || getBankID().isEmpty() || getCardNum().isEmpty() || getBankAddr().isEmpty()) {
                    CommonUtils.toast(context, "请认真填写");
                    return;
                }
                addCardPresenter.addCard();
                finish();
            }
        });

        addCardPresenter = new AddCardPresenter(this, context);
        addCardPresenter.initView();
    }

    @Override
    public void initView(final BankListBean bankListBean) {

        //选择器
        dataList = new ArrayList<String>();
        for (int i = 0; i < bankListBean.getData().size(); i++) {
            dataList.add(bankListBean.getData().get(i).getName());
        }

        picker = new OptionPicker(this, dataList);
        picker.setOffset(2);
        picker.setSelectedIndex(0);
        picker.setTextSize(15);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(int position, String option) {
                bankID = bankListBean.getData().get(position).getId();
                opBankActivityAddCard.setText(bankListBean.getData().get(position).getName());
//                toast(bankID);
            }
        });
    }

    @Override
    public String getUsername() {
        return etOwnerActivityAddCard.getText().toString().trim();
    }

    @Override
    public String getBankID() {
        return bankID;
    }

    @Override
    public String getCardNum() {
        return etNumActivityAddCard.getText().toString().trim();
    }

    @Override
    public String getBankAddr() {
        return etAddrActivityAddCard.getText().toString().trim();
    }

    @OnClick(R.id.op_bank_activity_add_card)
    public void onClick() {
        picker.show();
    }
}
