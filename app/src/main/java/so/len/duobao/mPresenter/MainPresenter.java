package so.len.duobao.mPresenter;

import so.len.duobao.mView.IMainView;

/**
 * Created by Chung on 2016/8/5.
 */
public class MainPresenter {
    private IMainView iMainView;

    public MainPresenter(IMainView iMainView) {
        this.iMainView = iMainView;
    }

    public void initView(){
        iMainView.initView();
    }

}
