package so.len.duobao.mPresenter;

import so.len.duobao.mView.IFiveView;

/**
 * Created by Chung on 2016/8/9.
 */
public class FivePresenter {
    private IFiveView iFiveView;

    public FivePresenter(IFiveView iFiveView) {
        this.iFiveView = iFiveView;
    }

    public void initView(){
        iFiveView.initView();
    }
}
