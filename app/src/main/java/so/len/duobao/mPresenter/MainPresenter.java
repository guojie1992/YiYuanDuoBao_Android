package so.len.duobao.mPresenter;

import so.len.duobao.mModel.IMainModel;
import so.len.duobao.mModel.MainModel;
import so.len.duobao.mView.IMainView;

/**
 * Created by Chung on 2016/8/5.
 */
public class MainPresenter {
    private IMainModel iMainModel;
    private IMainView iMainView;

    public MainPresenter(IMainView iMainView) {
        this.iMainModel = new MainModel();
        this.iMainView = iMainView;
    }

    public void initView(){
        iMainView.initView();
    }

}
