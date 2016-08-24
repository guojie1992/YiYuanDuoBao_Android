package so.len.duobao.mPresenter;

import android.content.Context;

import so.len.duobao.mListener.IHttpCompleteListener;
import so.len.duobao.mModel.AddAddrModel;
import so.len.duobao.mModel.IAddAddrModel;
import so.len.duobao.mView.IAddAddrView;
import so.len.duobao.utils.CommonUtils;

/**
 * Created by Chung on 2016/8/17.
 */
public class AddAddrPresenter {
    private Context context;
    private IAddAddrModel iAddAddrModel;
    private IAddAddrView iAddAddrView;

    public AddAddrPresenter(IAddAddrView iAddAddrView, Context context) {
        this.context = context;
        this.iAddAddrModel = new AddAddrModel(context);
        this.iAddAddrView = iAddAddrView;
    }

    public void initView(){
        iAddAddrView.initView();
    }

    public void addAddr(){
        iAddAddrModel.addAddr(new IHttpCompleteListener() {
            @Override
            public void loadComplete() {
                CommonUtils.toast(context, "添加成功");
            }

            @Override
            public void loadError(String msg) {
                CommonUtils.toast(context, msg);
            }
        }, iAddAddrView.getName(), iAddAddrView.getPhone(), iAddAddrView.getDistrict(), iAddAddrView.getDetail());
    }
}
