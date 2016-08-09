package so.len.duobao.iPresenter;

import so.len.duobao.iView.IFourView;

/**
 * Created by Chung on 2016/8/9.
 */
public class FourPresenter implements IFourPresenter {
    private IFourView iFourView;

    public FourPresenter(IFourView iFourView) {
        this.iFourView = iFourView;
    }

    @Override
    public void initTreasureList() {
        iFourView.initTreasureList();
    }
}
