package so.len.duobao.mPresenter;

import so.len.duobao.mView.IHistoryGiftsView;

/**
 * Created by Chung on 2016/8/10.
 */
public class HistoryGiftsPresenter {
    private IHistoryGiftsView iHistoryGiftsView;

    public HistoryGiftsPresenter(IHistoryGiftsView iHistoryGiftsView) {
        this.iHistoryGiftsView = iHistoryGiftsView;
    }

    public void initView(){
        iHistoryGiftsView.initView();
    }
}
