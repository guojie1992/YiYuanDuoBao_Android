package so.len.duobao.mPresenter;

import so.len.duobao.mView.IOneView;

/**
 * Created by Chung on 2016/8/8.
 */
public class OnePresenter {
    private IOneView iOneView;

    public OnePresenter(IOneView iOneView) {
        this.iOneView = iOneView;
    }

    public void initView(){
        iOneView.initView();
    }

}
