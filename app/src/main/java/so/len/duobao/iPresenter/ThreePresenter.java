package so.len.duobao.iPresenter;

import so.len.duobao.iView.IThreeView;

/**
 * Created by Chung on 2016/8/9.
 */
public class ThreePresenter implements IThreePresenter {
    private IThreeView iThreeView;

    public ThreePresenter(IThreeView iThreeView) {
        this.iThreeView = iThreeView;
    }

    @Override
    public void initPoint() {
        iThreeView.initPoint();
    }

    @Override
    public void initM() {
        iThreeView.initM();
    }

    @Override
    public void initSpeaker() {
        iThreeView.initSpeaker();
    }
}
