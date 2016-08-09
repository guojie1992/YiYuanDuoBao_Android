package so.len.duobao.iPresenter;

import so.len.duobao.iView.ITwoView;

/**
 * Created by Chung on 2016/8/9.
 */
public class TwoPresenter implements ITwoPresenter {
    private ITwoView iTwoView;

    public TwoPresenter(ITwoView iTwoView) {
        this.iTwoView = iTwoView;
    }

    @Override
    public void initImg() {
        iTwoView.initImg();
    }

    @Override
    public void initGoodsViewPager(){
        iTwoView.initGoodsViewPager();
    }

    @Override
    public void initGoalsGoods() {
        iTwoView.initGoalsGoods();
    }

    @Override
    public void initMGoods() {
        iTwoView.initMGoods();
    }
}
