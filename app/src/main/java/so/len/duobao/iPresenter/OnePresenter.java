package so.len.duobao.iPresenter;

import so.len.duobao.iView.IOneView;

/**
 * Created by Chung on 2016/8/8.
 */
public class OnePresenter implements IOnePresenter {
    private IOneView iOneView;

    public OnePresenter(IOneView iOneView) {
        this.iOneView = iOneView;
    }

    @Override
    public void initLoopViewPager() {
        iOneView.initLoopViewPager();
    }

    @Override
    public void initLotteryList() {
        iOneView.initLotteryList();
    }

    @Override
    public void initRefresh() {
        iOneView.initRefresh();
    }
}
