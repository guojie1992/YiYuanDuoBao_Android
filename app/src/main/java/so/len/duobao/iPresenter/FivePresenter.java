package so.len.duobao.iPresenter;

import so.len.duobao.iView.IFiveView;

/**
 * Created by Chung on 2016/8/9.
 */
public class FivePresenter implements IFivePresenter {
    private IFiveView iFiveView;

    public FivePresenter(IFiveView iFiveView) {
        this.iFiveView = iFiveView;
    }

    @Override
    public void initBtn() {
        iFiveView.initBtn();
    }

    @Override
    public void initGiftsViewPager() {
        iFiveView.initGiftsViewPager();
    }

    @Override
    public void initMyGifts() {
        iFiveView.initMyGifts();
    }

    @Override
    public void initHistroyGifts() {
        iFiveView.initHistroyGifts();
    }
}
