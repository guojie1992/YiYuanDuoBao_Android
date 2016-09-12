package so.len.duobao.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import so.len.duobao.R;
import so.len.duobao.customView.TopMenuBar;
import so.len.duobao.mPresenter.AddAddrPresenter;
import so.len.duobao.mView.IAddAddrView;
import so.len.duobao.utils.CommonUtils;

/**
 * Created by Chung on 2016/8/17.
 */
public class AddAddrActivity extends BaseActivity implements IAddAddrView {
    @BindView(R.id.tmb_activity_add_addr)
    TopMenuBar tmbActivityAddAddr;
    @BindView(R.id.et_name_activity_add_addr)
    EditText etNameActivityAddAddr;
    @BindView(R.id.et_phone_activity_add_addr)
    EditText etPhoneActivityAddAddr;
    @BindView(R.id.et_district_activity_add_addr)
    EditText etDistrictActivityAddAddr;
    @BindView(R.id.et_detail_activity_add_addr)
    EditText etDetailActivityAddAddr;

    private Context context;
    private AddAddrPresenter addAddrPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_addr);
        ButterKnife.bind(this);
        context = AddAddrActivity.this;
        tmbActivityAddAddr.setMenuTopPadding(statusHeight);
        contorl();
    }

    private void contorl() {
        addAddrPresenter = new AddAddrPresenter(this, context);
        addAddrPresenter.initView();
    }

    @Override
    public void initView() {

        tmbActivityAddAddr.setBackVisibility(View.VISIBLE);
        tmbActivityAddAddr.setTitleVisibility(View.VISIBLE);
        tmbActivityAddAddr.setMenuVisibility(View.VISIBLE);
        tmbActivityAddAddr.setBackSrc(R.mipmap.top_back);
        tmbActivityAddAddr.setTitleText("添加地址");
        tmbActivityAddAddr.setMenuSrc(R.mipmap.save);
        tmbActivityAddAddr.setOnBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tmbActivityAddAddr.setOnMenuClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getName().isEmpty() || getPhone().isEmpty() || getDistrict().isEmpty() || getDetail().isEmpty()){
                    CommonUtils.toast(context, "请认真填写");
                    return;
                }
                addAddrPresenter.addAddr();
                finish();
            }
        });
    }

    @Override
    public String getName() {
        return etNameActivityAddAddr.getText().toString().trim();
    }

    @Override
    public String getPhone() {
        return etPhoneActivityAddAddr.getText().toString().trim();
    }

    @Override
    public String getDistrict() {
        return etDistrictActivityAddAddr.getText().toString().trim();
    }

    @Override
    public String getDetail() {
        return etDetailActivityAddAddr.getText().toString().trim();
    }
}
