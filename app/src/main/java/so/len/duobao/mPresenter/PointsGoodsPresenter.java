package so.len.duobao.mPresenter;

import so.len.duobao.mView.IPointsGoodsView;

/**
 * Created by Chung on 2016/8/10.
 */
public class PointsGoodsPresenter {
    private IPointsGoodsView iPointsGoodsView;

    public PointsGoodsPresenter(IPointsGoodsView iPointsGoodsView) {
        this.iPointsGoodsView = iPointsGoodsView;
    }

    public void initView(){
        iPointsGoodsView.initView();
    }
}
