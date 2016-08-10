package so.len.duobao.iPresenter;

import so.len.duobao.iView.IThreeView;

/**
 * Created by Chung on 2016/8/9.
 */
public class ThreePresenter {
    private IThreeView iThreeView;

    public ThreePresenter(IThreeView iThreeView) {
        this.iThreeView = iThreeView;
    }

    public void initView(){
        iThreeView.initView();
    }
}
