package so.len.duobao.iPresenter;

import so.len.duobao.iView.IPointsGoodsView;

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
