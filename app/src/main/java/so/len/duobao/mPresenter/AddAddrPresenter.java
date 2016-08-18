package so.len.duobao.mPresenter;

import so.len.duobao.mModel.AddAddrModel;
import so.len.duobao.mModel.IAddAddrModel;
import so.len.duobao.mView.IAddAddrView;

/**
 * Created by Chung on 2016/8/17.
 */
public class AddAddrPresenter {
    private IAddAddrModel iAddAddrModel;
    private IAddAddrView iAddAddrView;

    public AddAddrPresenter(IAddAddrView iAddAddrView) {
        this.iAddAddrModel = new AddAddrModel();
        this.iAddAddrView = iAddAddrView;
    }

    public void initView(){
        iAddAddrView.initView();
    }
}
