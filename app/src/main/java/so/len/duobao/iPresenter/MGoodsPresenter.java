package so.len.duobao.iPresenter;

import so.len.duobao.iView.IMGoodsView;

/**
 * Created by Chung on 2016/8/10.
 */
public class MGoodsPresenter {
    private IMGoodsView imGoodsView;

    public MGoodsPresenter(IMGoodsView imGoodsView) {
        this.imGoodsView = imGoodsView;
    }

    public void initView(){
        imGoodsView.initView();
    }
}
