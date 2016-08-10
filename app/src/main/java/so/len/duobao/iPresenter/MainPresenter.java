package so.len.duobao.iPresenter;

import so.len.duobao.iModel.IMainModel;
import so.len.duobao.iModel.MainModel;
import so.len.duobao.iView.IMainView;

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
