package so.len.duobao.iPresenter;

import so.len.duobao.iModel.AddAddrModel;
import so.len.duobao.iModel.IAddAddrModel;
import so.len.duobao.iView.IAddAddrView;

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
