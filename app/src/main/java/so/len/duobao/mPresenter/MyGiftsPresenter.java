package so.len.duobao.mPresenter;

import so.len.duobao.mView.IMyGiftsView;

/**
 * Created by Chung on 2016/8/10.
 */
public class MyGiftsPresenter {
    private IMyGiftsView iMyGiftsView;

    public MyGiftsPresenter(IMyGiftsView iMyGiftsView) {
        this.iMyGiftsView = iMyGiftsView;
    }

    public void initView(){
        iMyGiftsView.initView();
    }
}
