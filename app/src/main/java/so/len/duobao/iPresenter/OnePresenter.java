package so.len.duobao.iPresenter;

import so.len.duobao.iView.IOneView;

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
