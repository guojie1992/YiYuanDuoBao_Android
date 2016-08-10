package so.len.duobao.iPresenter;

import so.len.duobao.iView.ITwoView;

/**
 * Created by Chung on 2016/8/9.
 */
public class TwoPresenter {
    private ITwoView iTwoView;

    public TwoPresenter(ITwoView iTwoView) {
        this.iTwoView = iTwoView;
    }

    public void initView(){
        iTwoView.initView();
    }
}
