package so.len.duobao.iPresenter;

import so.len.duobao.iView.IFourView;

/**
 * Created by Chung on 2016/8/9.
 */
public class FourPresenter  {
    private IFourView iFourView;

    public FourPresenter(IFourView iFourView) {
        this.iFourView = iFourView;
    }

    public void initView(){
        iFourView.initView();
    }
}
