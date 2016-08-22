package so.len.duobao.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import so.len.duobao.R;
import so.len.duobao.customView.TopMenuBar;
import so.len.duobao.mPresenter.AddAddrPresenter;
import so.len.duobao.mView.IAddAddrView;

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

    private AddAddrPresenter addAddrPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_addr);
        ButterKnife.bind(this);
        tmbActivityAddAddr.setMenuTopPadding(statusHeight);
        contorl();
    }

    private void contorl() {
        addAddrPresenter = new AddAddrPresenter(this);
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
                toast("save");
                finish();
            }
        });
    }
}
