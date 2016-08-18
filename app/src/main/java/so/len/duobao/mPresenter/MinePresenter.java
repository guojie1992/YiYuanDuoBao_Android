package so.len.duobao.mPresenter;

import so.len.duobao.mView.IMineView;

/**
 * Created by Chung on 2016/8/11.
 */
public class MinePresenter {
    private IMineView iMineView;

    public MinePresenter(IMineView iMineView) {
        this.iMineView = iMineView;
    }

    public void initView(){
        iMineView.initView();
    }
}
