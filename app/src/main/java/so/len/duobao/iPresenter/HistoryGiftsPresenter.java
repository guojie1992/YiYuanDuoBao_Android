package so.len.duobao.iPresenter;

import so.len.duobao.iView.IHistoryGiftsView;

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
